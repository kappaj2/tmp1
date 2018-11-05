package com.ktk.orca.core.service.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ktk.orca.core.aop.DataA;
import com.ktk.orca.core.api.client.ProductsApi;
import com.ktk.orca.core.api.invoker.ApiClient;
import com.ktk.orca.core.api.model.ProductCatalogResponse;
import com.ktk.orca.core.api.model.ProductListResponse;
import com.ktk.orca.core.config.ApplicationProperties;
import com.ktk.orca.core.exceptions.OrcaApiException;
import com.ktk.orca.core.exceptions.RestTemplateError;
import com.ktk.orca.core.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestClientException;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@Validated
public class ProductServiceImpl implements ProductService {

    @Value("${application.clientApiDebugEnabled}")
    private boolean clientApiDebugEnabled;

    private ProductsApi productsApi;
    private ApplicationProperties props;
    private BaseServiceUtil baseServiceUtil;
    private MessageSource messageSource;

    public ProductServiceImpl(ProductsApi productsApi,
                              ApplicationProperties props,
                              BaseServiceUtil baseServiceUtil,
                              MessageSource messageSource) {
        this.productsApi = productsApi;
        this.props = props;
        this.baseServiceUtil = baseServiceUtil;
        this.messageSource = messageSource;
    }

    @PostConstruct
    private void setupRest() {
        productsApi.getApiClient().setBasePath(props.getOrcaApi().getBaseUri());
    }

    /**
     * Get a structured Product list forming a catalog grouped by:
     * Product Category
     * Vendor
     *
     * @return ProductCatalogResponse
     */
    @Override
    public ProductCatalogResponse getProductCatalog(@DataA final List<String> categories, @DataA final List<String> vendors) throws OrcaApiException {

        int responseCode = -1;
        Instant startTime = Instant.now();
        String responseMessage = null;
        ProductCatalogResponse productCatalogResponse = null;
        ObjectNode objectNode = baseServiceUtil.createNewObjectNode();

        try {
            objectNode.put("categories", categories.toString());
            objectNode.put("vendors", vendors.toString());

            ApiClient apiClient = productsApi.getApiClient();
            apiClient.setDebugging(clientApiDebugEnabled);

            productCatalogResponse = productsApi.getProductCatalog(categories, vendors);

            responseCode = apiClient.getStatusCode().value();
            responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

            return productCatalogResponse;

        } catch (RestClientException rce) {

            responseCode = 500;
            responseMessage = messageSource.getMessage("ktk.api.rest.product.exception", null, LocaleContextHolder.getLocale());
            throw new OrcaApiException(responseMessage, baseServiceUtil.buildBaseException(rce, responseCode), rce);

        } catch (RestTemplateError rte) {
            responseCode = rte.getApiError().getStatusCode();
            responseMessage = rte.getApiError().getMessage();
            throw new OrcaApiException(responseMessage, rte.getApiError());

        } finally {

            baseServiceUtil.writeTransactionRecord("ProductService",
                    "getProductCatalog",
                    startTime,
                    objectNode,
                    responseCode,
                    responseMessage,
                    productCatalogResponse);
        }
    }

    /**
     * Get Product list meeting query criteria. Paged result list.
     *
     * @param categories List of categories to include in the search.
     * @param vendors    List of vendors to include in the search.
     * @param pageSize   Page size for returned data.
     * @param pageStart  Starting offset for paged returns.
     * @return ProductCatalogFlatResponse
     */
    @Override
    public ProductListResponse getProductList(@DataA final List<String> categories, @DataA final List<String> vendors, @DataA final Integer pageStart, @DataA final Integer pageSize) throws OrcaApiException {

        int responseCode = -1;
        Instant startTime = Instant.now();
        String responseMessage = null;
        ProductListResponse productListResponse = null;
        ObjectNode objectNode = baseServiceUtil.createNewObjectNode();

        try {
            objectNode.put("categories", categories.toString());
            objectNode.put("vendors", vendors.toString());
            objectNode.put("pageSize", pageSize);
            objectNode.put("pageStart", pageStart);

            ApiClient apiClient = productsApi.getApiClient();
            apiClient.setDebugging(clientApiDebugEnabled);

            productListResponse = productsApi.getProductList(categories, vendors, pageStart, pageSize);

            responseCode = apiClient.getStatusCode().value();
            responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

            return productListResponse;

        } catch (RestClientException rce) {

            responseCode = 500;
            responseMessage = messageSource.getMessage("ktk.api.rest.product.exception", null, LocaleContextHolder.getLocale());
            throw new OrcaApiException(responseMessage, baseServiceUtil.buildBaseException(rce, responseCode), rce);

        } catch (RestTemplateError rte) {
            responseCode = rte.getApiError().getStatusCode();
            responseMessage = rte.getApiError().getMessage();
            throw new OrcaApiException(responseMessage, rte.getApiError());

        } finally {

            baseServiceUtil.writeTransactionRecord("ProductService",
                    "getProductList",
                    startTime,
                    objectNode,
                    responseCode,
                    responseMessage,
                    productListResponse);

        }
    }
}
