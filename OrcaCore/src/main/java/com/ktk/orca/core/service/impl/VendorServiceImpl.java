package com.ktk.orca.core.service.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ktk.orca.core.aop.DataA;
import com.ktk.orca.core.api.client.VendorsApi;
import com.ktk.orca.core.api.invoker.ApiClient;
import com.ktk.orca.core.api.model.ProductVendorsResponse;
import com.ktk.orca.core.config.ApplicationProperties;
import com.ktk.orca.core.exceptions.OrcaApiException;
import com.ktk.orca.core.exceptions.RestTemplateError;
import com.ktk.orca.core.service.VendorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestClientException;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Slf4j
@Service
@Validated
public class VendorServiceImpl implements VendorService {

    @Value("${application.clientApiDebugEnabled}")
    private boolean clientApiDebugEnabled;

    private VendorsApi vendorsApi;
    private ApplicationProperties props;
    private BaseServiceUtil baseServiceUtil;
    private MessageSource messageSource;

    public VendorServiceImpl(VendorsApi vendorsApi,
                             ApplicationProperties props,
                             BaseServiceUtil baseServiceUtil,
                             MessageSource messageSource) {
        this.vendorsApi = vendorsApi;
        this.props = props;
        this.baseServiceUtil = baseServiceUtil;
        this.messageSource = messageSource;
    }

    @PostConstruct
    private void setupRest() {
        vendorsApi.getApiClient().setBasePath(props.getOrcaApi().getBaseUri());
    }

    /**
     * Get Vendor list. Paged result list.
     *
     * @param includeProducts
     * @return ProductVendorsResponse
     */
    @Override
    public ProductVendorsResponse getVendorList(@DataA final Boolean includeProducts) throws OrcaApiException {

        int responseCode = -1;
        Instant startTime = Instant.now();
        String responseMessage = null;
        ProductVendorsResponse productVendorsResponse = null;
        ObjectNode objectNode = baseServiceUtil.createNewObjectNode();

        try {
            objectNode.put("includeProducts", includeProducts);

            ApiClient apiClient = vendorsApi.getApiClient();
            apiClient.setDebugging(clientApiDebugEnabled);

            productVendorsResponse = vendorsApi.getVendorList(includeProducts);

            responseCode = apiClient.getStatusCode().value();
            responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

            return productVendorsResponse;

        } catch (RestClientException rce) {

            responseCode = 500;
            responseMessage = messageSource.getMessage("ktk.api.rest.vendor.exception", null, LocaleContextHolder.getLocale());
            throw new OrcaApiException(responseMessage, baseServiceUtil.buildBaseException(rce, responseCode), rce);

        } catch (RestTemplateError rte) {
            responseCode = rte.getApiError().getStatusCode();
            responseMessage = rte.getApiError().getMessage();
            throw new OrcaApiException(responseMessage, rte.getApiError());

        } finally {

            baseServiceUtil.writeTransactionRecord("VendorService",
                    "getVendorList",
                    startTime,
                    objectNode,
                    responseCode,
                    responseMessage,
                    productVendorsResponse);
        }
    }
}
