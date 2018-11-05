package com.ktk.orca.core.service.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ktk.orca.core.aop.DataA;
import com.ktk.orca.core.api.client.PurchasesApi;
import com.ktk.orca.core.api.invoker.ApiClient;
import com.ktk.orca.core.api.model.*;
import com.ktk.orca.core.config.ApplicationProperties;
import com.ktk.orca.core.exceptions.OrcaApiException;
import com.ktk.orca.core.exceptions.RestTemplateError;
import com.ktk.orca.core.service.PurchaseService;
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
public class PurchaseServiceImpl implements PurchaseService {

    @Value("${application.clientApiDebugEnabled}")
    private boolean clientApiDebugEnabled;

    private PurchasesApi purchasesApi;
    private ApplicationProperties props;
    private BaseServiceUtil baseServiceUtil;
    private MessageSource messageSource;

    public PurchaseServiceImpl(PurchasesApi purchasesApi,
                               ApplicationProperties props,
                               BaseServiceUtil baseServiceUtil,
                               MessageSource messageSource) {
        this.purchasesApi = purchasesApi;
        this.props = props;
        this.baseServiceUtil = baseServiceUtil;
        this.messageSource = messageSource;
    }

    @PostConstruct
    private void setupRest() {
        purchasesApi.getApiClient().setBasePath(props.getOrcaApi().getBaseUri());
    }

    /**
     * @param purchaseRequest
     * @return
     * @throws OrcaApiException
     */
    @Override
    public PurchaseResponse createPurchase(@DataA final PurchaseRequest purchaseRequest) throws OrcaApiException {

        int responseCode = -1;
        Instant startTime = Instant.now();
        String responseMessage = null;
        PurchaseResponse purchaseResponse = null;
        ObjectNode objectNode = baseServiceUtil.createNewObjectNode();

        try {
            objectNode.put("purchaseRequest", baseServiceUtil.parseObjectToString(purchaseRequest));

            ApiClient apiClient = purchasesApi.getApiClient();
            apiClient.setDebugging(clientApiDebugEnabled);

            purchaseResponse = purchasesApi.createPurchase(purchaseRequest);

            responseCode = apiClient.getStatusCode().value();
            responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

            return purchaseResponse;

        } catch (RestClientException rce) {

            responseCode = 500;
            responseMessage = messageSource.getMessage("ktk.api.rest.purchase.exception", null, LocaleContextHolder.getLocale());
            throw new OrcaApiException(responseMessage, baseServiceUtil.buildBaseException(rce, responseCode), rce);

        } catch (RestTemplateError rte) {
            responseCode = rte.getApiError().getStatusCode();
            responseMessage = rte.getApiError().getMessage();
            throw new OrcaApiException(responseMessage, rte.getApiError());

        } finally {

            baseServiceUtil.writeTransactionRecord("PurchaseService",
                    "createPurchase",
                    startTime,
                    objectNode,
                    responseCode,
                    responseMessage,
                    purchaseResponse);
        }
    }

    /**
     * Retrieve purchase details.
     *
     * @param purchaseId
     * @return PurchaseDetailsResponse
     */
    @Override
    public PurchaseDetailsResponse getPurchaseDetails(@DataA final String purchaseId) throws OrcaApiException {

        int responseCode = -1;
        Instant startTime = Instant.now();
        String responseMessage = null;
        PurchaseDetailsResponse purchaseDetailsResponse = null;
        ObjectNode objectNode = baseServiceUtil.createNewObjectNode();

        try {
            objectNode.put("purchaseId", purchaseId);

            ApiClient apiClient = purchasesApi.getApiClient();
            apiClient.setDebugging(clientApiDebugEnabled);

            purchaseDetailsResponse = purchasesApi.getPurchase(purchaseId);

            responseCode = apiClient.getStatusCode().value();
            responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

            return purchaseDetailsResponse;

        } catch (RestClientException rce) {

            responseCode = 500;
            responseMessage = messageSource.getMessage("ktk.api.rest.purchase.exception", null, LocaleContextHolder.getLocale());
            throw new OrcaApiException(responseMessage, baseServiceUtil.buildBaseException(rce, responseCode), rce);

        } catch (RestTemplateError rte) {
            responseCode = rte.getApiError().getStatusCode();
            responseMessage = rte.getApiError().getMessage();
            throw new OrcaApiException(responseMessage, rte.getApiError());

        } finally {

            baseServiceUtil.writeTransactionRecord("PurchaseService",
                    "getPurchaseDetails",
                    startTime,
                    objectNode,
                    responseCode,
                    responseMessage,
                    purchaseDetailsResponse);
        }
    }

    /**
     * Obtain explicit authorisation from the customer for the purchase.
     * <p>
     * Where the purchase has the following requirements:
     * requires a multi-step authortisation, or
     * where the vendor will take actions that result in a deviation from the purchase face value, for example:
     * the municipality deducts account arrears from the pre-paid electricity purchase
     *
     * @param purchaseId
     * @param purchaseAuthorisationRequest
     * @return PurchaseResponse
     */
    @Override
    public PurchaseResponse authPurchase(@DataA final String purchaseId, @DataA final PurchaseAuthorisationRequest purchaseAuthorisationRequest) throws OrcaApiException {

        int responseCode = -1;
        Instant startTime = Instant.now();
        String responseMessage = null;
        PurchaseResponse purchaseResponse = null;
        ObjectNode objectNode = baseServiceUtil.createNewObjectNode();

        try {
            objectNode.put("purchaseId", purchaseId);
            objectNode.put("purchaseAuthorisationRequest", baseServiceUtil.parseObjectToString(purchaseAuthorisationRequest));

            ApiClient apiClient = purchasesApi.getApiClient();
            apiClient.setDebugging(clientApiDebugEnabled);

            purchaseResponse = purchasesApi.authPurchase(purchaseId, purchaseAuthorisationRequest);

            responseCode = apiClient.getStatusCode().value();
            responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

            return purchaseResponse;

        } catch (RestClientException rce) {

            responseCode = 500;
            responseMessage = messageSource.getMessage("ktk.api.rest.purchase.exception", null, LocaleContextHolder.getLocale());
            throw new OrcaApiException(responseMessage, baseServiceUtil.buildBaseException(rce, responseCode), rce);

        } catch (RestTemplateError rte) {
            responseCode = rte.getApiError().getStatusCode();
            responseMessage = rte.getApiError().getMessage();
            throw new OrcaApiException(responseMessage, rte.getApiError());

        } finally {

            baseServiceUtil.writeTransactionRecord("PurchaseService",
                    "authPurchase",
                    startTime,
                    objectNode,
                    responseCode,
                    responseMessage,
                    purchaseResponse);
        }
    }

    /**
     * Retrieve purchase response message for previously purchased product. This is primarily to be used as an error recovery mechanism in the event of a communication failure or timeout during a purchase operation.
     *
     * @param purchaseId             The id field value from the purchase created.
     * @param purchaseReprintRequest
     * @return PurchaseResponse
     */
    @Override
    public PurchaseResponse reprintReceipt(@DataA final String purchaseId, @DataA final PurchaseReprintRequest purchaseReprintRequest) throws OrcaApiException {

        int responseCode = -1;
        Instant startTime = Instant.now();
        String responseMessage = null;
        PurchaseResponse purchaseResponse = null;
        ObjectNode objectNode = baseServiceUtil.createNewObjectNode();

        try {
            objectNode.put("purchaseId", purchaseId);
            objectNode.put("purchaseReprintRequest", baseServiceUtil.parseObjectToString(purchaseReprintRequest));

            ApiClient apiClient = purchasesApi.getApiClient();
            apiClient.setDebugging(clientApiDebugEnabled);

            purchaseResponse = purchasesApi.reprintReceipt(purchaseId, purchaseReprintRequest);

            responseCode = apiClient.getStatusCode().value();
            responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

            return purchaseResponse;

        } catch (RestClientException rce) {

            responseCode = 500;
            responseMessage = messageSource.getMessage("ktk.api.rest.purchase.exception", null, LocaleContextHolder.getLocale());
            throw new OrcaApiException(responseMessage, baseServiceUtil.buildBaseException(rce, responseCode), rce);

        } catch (RestTemplateError rte) {
            responseCode = rte.getApiError().getStatusCode();
            responseMessage = rte.getApiError().getMessage();
            throw new OrcaApiException(responseMessage, rte.getApiError());

        } finally {

            baseServiceUtil.writeTransactionRecord("PurchaseService",
                    "reprintReceipt",
                    startTime,
                    objectNode,
                    responseCode,
                    responseMessage,
                    purchaseResponse);

        }
    }
}
