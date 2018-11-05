package com.ktk.orca.core.service.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ktk.orca.core.aop.DataA;
import com.ktk.orca.core.api.client.CategoriesApi;
import com.ktk.orca.core.api.invoker.ApiClient;
import com.ktk.orca.core.api.model.ProductCategoryResponse;
import com.ktk.orca.core.config.ApplicationProperties;
import com.ktk.orca.core.exceptions.OrcaApiException;
import com.ktk.orca.core.exceptions.RestTemplateError;
import com.ktk.orca.core.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    @Value("${application.clientApiDebugEnabled}")
    private boolean clientApiDebugEnabled;

    private CategoriesApi categoriesApi;
    private ApplicationProperties props;
    private BaseServiceUtil baseServiceUtil;
    private MessageSource messageSource;

    public CategoryServiceImpl(CategoriesApi categoriesApi,
                               ApplicationProperties props,
                               BaseServiceUtil baseServiceUtil,
                               MessageSource messageSource) {
        this.categoriesApi = categoriesApi;
        this.props = props;
        this.baseServiceUtil = baseServiceUtil;
        this.messageSource = messageSource;
    }

    @PostConstruct
    private void setupRest() {
        categoriesApi.getApiClient().setBasePath(props.getOrcaApi().getBaseUri());
    }

    /**
     * Get Product Category list.
     * <p>
     * If includeProducts=true, then includeVendors is implied and does not need to be specified
     *
     * @param includeVendors  Include vendors in the search.
     * @param includeProducts Include products in the search.
     * @return ProductCategoryResponse
     */
    public ProductCategoryResponse getProductCategoryList(@DataA final Boolean includeVendors, @DataA final Boolean includeProducts) throws OrcaApiException {

        int responseCode = -1;
        Instant startTime = Instant.now();
        String responseMessage = null;
        ProductCategoryResponse productCategoryResponse = null;
        ObjectNode objectNode = baseServiceUtil.createNewObjectNode();

        try {
            objectNode.put("includeVendors", includeVendors);
            objectNode.put("includeProducts", includeProducts);

            ApiClient apiClient = categoriesApi.getApiClient();
            apiClient.setDebugging(clientApiDebugEnabled);

            productCategoryResponse = categoriesApi.getProductCategoryList(includeVendors, includeProducts);

            responseCode = apiClient.getStatusCode().value();
            responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

            return productCategoryResponse;

        } catch (RestClientException rce) {

            responseCode = 500;
            responseMessage = messageSource.getMessage("ktk.api.rest.category.category.exception", null, LocaleContextHolder.getLocale());
            throw new OrcaApiException(responseMessage, baseServiceUtil.buildBaseException(rce, responseCode), rce);

        } catch (RestTemplateError rte) {
            responseCode = rte.getApiError().getStatusCode();
            responseMessage = rte.getApiError().getMessage();
            throw new OrcaApiException(responseMessage, rte.getApiError());

        } finally {

            baseServiceUtil.writeTransactionRecord("CategoryService",
                    "getProductCategoryList",
                    startTime,
                    objectNode,
                    responseCode,
                    responseMessage,
                    productCategoryResponse);
        }
    }
}
