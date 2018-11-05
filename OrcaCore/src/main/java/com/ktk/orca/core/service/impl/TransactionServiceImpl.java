package com.ktk.orca.core.service.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ktk.orca.core.aop.DataA;
import com.ktk.orca.core.api.client.TransactionListApi;
import com.ktk.orca.core.api.invoker.ApiClient;
import com.ktk.orca.core.api.model.TransactionListProcessingResponse;
import com.ktk.orca.core.api.model.TransactionReportStatusResponse;
import com.ktk.orca.core.config.ApplicationProperties;
import com.ktk.orca.core.exceptions.OrcaApiException;
import com.ktk.orca.core.exceptions.RestTemplateError;
import com.ktk.orca.core.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestClientException;
import org.threeten.bp.LocalDate;

import javax.annotation.PostConstruct;
import java.time.Instant;


@Slf4j
@Service
@Validated
public class TransactionServiceImpl implements TransactionService {

    @Value("${application.clientApiDebugEnabled}")
    private boolean clientApiDebugEnabled;

    private TransactionListApi transactionListApi;
    private ApplicationProperties props;
    private BaseServiceUtil baseServiceUtil;
    private MessageSource messageSource;

    public TransactionServiceImpl(TransactionListApi transactionListApi,
                                  ApplicationProperties props,
                                  BaseServiceUtil baseServiceUtil,
                                  MessageSource messageSource) {
        this.transactionListApi = transactionListApi;
        this.props = props;
        this.baseServiceUtil = baseServiceUtil;
        this.messageSource = messageSource;
    }

    @PostConstruct
    private void setupRest() {
        transactionListApi.getApiClient().setBasePath(props.getOrcaApi().getBaseUri());
    }

    /**
     * Transactions for the period. Default period - month to date
     *
     * @param fromDate fromSearchDate
     * @param toDate   toSearchDate
     * @param type     Transaction type to search for. Available values : ALL, COMMISSION, CORRECTION, FEES, PURCHASES, DEPOSIT, WITHDRAW
     * @return TransactionListProcessingResponse
     */
    @Override
    public TransactionListProcessingResponse getTransactions(@DataA final LocalDate fromDate, @DataA final LocalDate toDate, @DataA final String type, @DataA final Integer pageStart, @DataA final Integer pageSize) throws OrcaApiException {

        int responseCode = -1;
        Instant startTime = Instant.now();
        String responseMessage = null;
        TransactionListProcessingResponse transactionListProcessingResponse = null;
        ObjectNode objectNode = baseServiceUtil.createNewObjectNode();

        try {
            objectNode.put("fromDate", fromDate.toString());
            objectNode.put("toDate", toDate.toString());
            objectNode.put("type", type);
            objectNode.put("pageStart", pageStart);
            objectNode.put("pageSize", pageSize);

            ApiClient apiClient = transactionListApi.getApiClient();
            apiClient.setDebugging(clientApiDebugEnabled);

            transactionListProcessingResponse = transactionListApi.getTransactions(fromDate, toDate, type, pageStart, pageSize);

            responseCode = apiClient.getStatusCode().value();
            responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

            return transactionListProcessingResponse;

        } catch (RestClientException rce) {

            responseCode = 500;
            responseMessage = messageSource.getMessage("ktk.api.rest.transaction.exception", null, LocaleContextHolder.getLocale());
            throw new OrcaApiException(responseMessage, baseServiceUtil.buildBaseException(rce, responseCode), rce);

        } catch (RestTemplateError rte) {
            responseCode = rte.getApiError().getStatusCode();
            responseMessage = rte.getApiError().getMessage();
            throw new OrcaApiException(responseMessage, rte.getApiError());

        } finally {

            baseServiceUtil.writeTransactionRecord("TransactionService",
                    "getTransactions",
                    startTime,
                    objectNode,
                    responseCode,
                    responseMessage,
                    transactionListProcessingResponse);

        }
    }

    /**
     * Check status of transaction report / listing previously requested
     *
     * @param transactionListId
     * @return TransactionReportStatusResponse
     */
    @Override
    public TransactionReportStatusResponse getTransactionReportStatus(@DataA final String transactionListId) throws OrcaApiException {

        int responseCode = -1;
        Instant startTime = Instant.now();
        String responseMessage = null;
        TransactionReportStatusResponse transactionReportStatusResponse = null;
        ObjectNode objectNode = baseServiceUtil.createNewObjectNode();

        try {
            objectNode.put("transactionListId", transactionListId);

            ApiClient apiClient = transactionListApi.getApiClient();
            apiClient.setDebugging(clientApiDebugEnabled);

            transactionReportStatusResponse = transactionListApi.getTransactionReportStatus(transactionListId);

            responseCode = apiClient.getStatusCode().value();
            responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

            return transactionReportStatusResponse;

        } catch (RestClientException rce) {

            responseCode = 500;
            responseMessage = messageSource.getMessage("ktk.api.rest.transaction.exception", null, LocaleContextHolder.getLocale());
            throw new OrcaApiException(responseMessage, baseServiceUtil.buildBaseException(rce, responseCode), rce);

        } catch (RestTemplateError rte) {
            responseCode = rte.getApiError().getStatusCode();
            responseMessage = rte.getApiError().getMessage();
            throw new OrcaApiException(responseMessage, rte.getApiError());

        } finally {

            baseServiceUtil.writeTransactionRecord("TransactionService",
                    "getTransactionReportStatus",
                    startTime,
                    objectNode,
                    responseCode,
                    responseMessage,
                    transactionReportStatusResponse);
        }
    }
}
