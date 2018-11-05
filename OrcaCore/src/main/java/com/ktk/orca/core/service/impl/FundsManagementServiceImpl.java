package com.ktk.orca.core.service.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ktk.orca.core.aop.DataA;
import com.ktk.orca.core.api.client.FundsManagementApi;
import com.ktk.orca.core.api.invoker.ApiClient;
import com.ktk.orca.core.api.model.AccountFundsResponse;
import com.ktk.orca.core.api.model.AccountFundsTransaction;
import com.ktk.orca.core.api.model.AccountFundsTransactionResponse;
import com.ktk.orca.core.config.ApplicationProperties;
import com.ktk.orca.core.exceptions.OrcaApiException;
import com.ktk.orca.core.exceptions.RestTemplateError;
import com.ktk.orca.core.service.FundsManagementService;
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
public class FundsManagementServiceImpl implements FundsManagementService {

    @Value("${application.clientApiDebugEnabled}")
    private boolean clientApiDebugEnabled;

    private FundsManagementApi fundsManagementApi;
    private ApplicationProperties props;
    private BaseServiceUtil baseServiceUtil;
    private MessageSource messageSource;

    public FundsManagementServiceImpl(FundsManagementApi fundsManagementApi,
                                      ApplicationProperties props,
                                      BaseServiceUtil baseServiceUtil,
                                      MessageSource messageSource) {
        this.fundsManagementApi = fundsManagementApi;
        this.props = props;
        this.baseServiceUtil = baseServiceUtil;
        this.messageSource = messageSource;
    }

    @PostConstruct
    private void setupRest() {
        fundsManagementApi.getApiClient().setBasePath(props.getOrcaApi().getBaseUri());
    }

    /**
     * Get funds balances for account.
     *
     * @param accountId AccountId to return the account status for.
     * @return AccountFundsTransactionResponse
     */
    @Override
    public AccountFundsResponse getAccountFunds(@DataA final String accountId) throws OrcaApiException {

        int responseCode = -1;
        Instant startTime = Instant.now();
        String responseMessage = null;
        AccountFundsResponse accountFundsResponse = null;
        ObjectNode objectNode = baseServiceUtil.createNewObjectNode();

        try {
            objectNode.put("accountId", accountId);

            ApiClient apiClient = fundsManagementApi.getApiClient();
            apiClient.setDebugging(clientApiDebugEnabled);

            accountFundsResponse = fundsManagementApi.getAccountFunds(accountId);

            responseCode = apiClient.getStatusCode().value();
            responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

            return accountFundsResponse;

        } catch (RestClientException rce) {

            responseCode = 500;
            responseMessage = messageSource.getMessage("ktk.api.rest.funds.exception", null, LocaleContextHolder.getLocale());
            throw new OrcaApiException(responseMessage, baseServiceUtil.buildBaseException(rce, responseCode), rce);

        } catch (RestTemplateError rte) {
            responseCode = rte.getApiError().getStatusCode();
            responseMessage = rte.getApiError().getMessage();
            throw new OrcaApiException(responseMessage, rte.getApiError());

        } finally {

            baseServiceUtil.writeTransactionRecord("FundsManagementService",
                    "getAccountFunds",
                    startTime,
                    objectNode,
                    responseCode,
                    responseMessage,
                    accountFundsResponse);
        }
    }

    /**
     * Create funds deposit or withdrawal notification.
     *
     * @param accountId               AccountId to return the transaction history for.
     * @param accountFundsTransaction
     * @return AccountFundsTransactionResponse
     */
    @Override
    public AccountFundsTransactionResponse notifyAccountFundsTransaction(@DataA final String accountId, @DataA final AccountFundsTransaction accountFundsTransaction) throws OrcaApiException {

        int responseCode = -1;
        Instant startTime = Instant.now();
        String responseMessage = null;
        AccountFundsTransactionResponse accountFundsTransactionResponse = null;
        ObjectNode objectNode = baseServiceUtil.createNewObjectNode();

        try {
            objectNode.put("accountId", accountId);
            objectNode.put("accountFundsTransaction", baseServiceUtil.parseObjectToString(accountFundsTransaction));

            ApiClient apiClient = fundsManagementApi.getApiClient();
            apiClient.setDebugging(clientApiDebugEnabled);

            accountFundsTransactionResponse = fundsManagementApi.notifyAccountFundsTransaction(accountId, accountFundsTransaction);

            responseCode = apiClient.getStatusCode().value();
            responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

            return accountFundsTransactionResponse;

        } catch (RestClientException rce) {

            responseCode = 500;
            responseMessage = messageSource.getMessage("ktk.api.rest.funds.exception", null, LocaleContextHolder.getLocale());
            throw new OrcaApiException(responseMessage, baseServiceUtil.buildBaseException(rce, responseCode), rce);

        } catch (RestTemplateError rte) {
            responseCode = rte.getApiError().getStatusCode();
            responseMessage = rte.getApiError().getMessage();
            throw new OrcaApiException(responseMessage, rte.getApiError());

        } finally {

            baseServiceUtil.writeTransactionRecord("FundsManagementService",
                    "notifyAccountFundsTransaction",
                    startTime,
                    objectNode,
                    responseCode,
                    responseMessage,
                    accountFundsTransactionResponse);
        }
    }
}
