package com.ktk.orca.core.service.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ktk.orca.core.aop.DataA;
import com.ktk.orca.core.api.client.AccountApi;
import com.ktk.orca.core.api.invoker.ApiClient;
import com.ktk.orca.core.api.model.AccountDetailsResponse;
import com.ktk.orca.core.config.ApplicationProperties;
import com.ktk.orca.core.exceptions.OrcaApiException;
import com.ktk.orca.core.exceptions.RestTemplateError;
import com.ktk.orca.core.service.AccountService;
import com.ktk.orca.core.utilities.DateUtils;
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
import java.util.Calendar;


@Slf4j
@Service
@Validated
public class AccountServiceImpl implements AccountService {

    @Value("${application.clientApiDebugEnabled}")
    private boolean clientApiDebugEnabled;

    private AccountApi accountApi;
    private ApplicationProperties props;
    private BaseServiceUtil baseServiceUtil;
    private MessageSource messageSource;

    public AccountServiceImpl(AccountApi accountApi,
                              ApplicationProperties props,
                              BaseServiceUtil baseServiceUtil,
                              MessageSource messageSource) {
        this.accountApi = accountApi;
        this.props = props;
        this.baseServiceUtil = baseServiceUtil;
        this.messageSource = messageSource;
    }

    @PostConstruct
    private void setupRest() {
        accountApi.getApiClient().setBasePath(props.getOrcaApi().getBaseUri());
    }

    /**
     * Return account details. Default - returns account summary with purchases totals by product category for the calendar month to date.
     * Additional details are requested via the following query parameters:
     *
     * @param accountId        AccountId to search for.
     * @param fromDate         Search date from.
     * @param toDate           Search date to.
     * @param purchasesSummary Include last 30 product purchase transactions.
     * @param vendorSummary    Include per vendor breakdown in product category totals.
     * @return AccountDetailsResponse
     */
    public AccountDetailsResponse getAccountDetails(@DataA final String accountId, @DataA final LocalDate fromDate, @DataA final LocalDate toDate, @DataA final Boolean purchasesSummary, @DataA final Boolean vendorSummary) throws OrcaApiException {

        int responseCode = -1;
        Instant startTime = Instant.now();
        String responseMessage = null;
        AccountDetailsResponse accountDetailsResponse = null;
        ObjectNode objectNode = baseServiceUtil.createNewObjectNode();

        try {
            objectNode.put("accountId", accountId);
            objectNode.put("fromDate", fromDate.toString());
            objectNode.put("toDate", toDate.toString());
            objectNode.put("purchasesSummary", purchasesSummary);
            objectNode.put("vendorSummary", vendorSummary);

            ApiClient apiClient = accountApi.getApiClient();
            apiClient.setDebugging(clientApiDebugEnabled);

            accountDetailsResponse = accountApi.getAccount(accountId, fromDate, toDate, purchasesSummary, vendorSummary);

            responseCode = apiClient.getStatusCode().value();

            responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

            return accountDetailsResponse;

        } catch (RestClientException rce) {

            responseCode = 500;
            responseMessage = messageSource.getMessage("ktk.api.rest.account.exception", null, LocaleContextHolder.getLocale());
            throw new OrcaApiException(responseMessage, baseServiceUtil.buildBaseException(rce, responseCode), rce);

        } catch (RestTemplateError rte) {
            responseCode = rte.getApiError().getStatusCode();
            responseMessage = rte.getApiError().getMessage();
            throw new OrcaApiException(responseMessage, rte.getApiError());

        } finally {

            baseServiceUtil.writeTransactionRecord("AccountService",
                    "getAccountDetails",
                    startTime,
                    objectNode,
                    responseCode,
                    responseMessage,
                    accountDetailsResponse);
        }
    }

    /**
     * Return account details. Default - returns account summary with purchases totals by product category for the calendar month to date.
     * Search date will be set for the last month.
     *
     * @param accountId        AccountId to search for.
     * @param purchasesSummary Include last 30 product purchase transactions.
     * @param vendorSummary    Include per vendor breakdown in product category totals.
     * @return AccountDetailsResponse
     */
    @Override
    public AccountDetailsResponse getCurrentMonthAccountDetails(@DataA final String accountId, @DataA final Boolean purchasesSummary, @DataA final Boolean vendorSummary) throws OrcaApiException {

        Calendar calNow = Calendar.getInstance();
        calNow.set(Calendar.DAY_OF_MONTH, 0);

        int steps = Calendar.DAY_OF_MONTH;

        LocalDate fromDate = DateUtils.getZonedDate(steps);
        LocalDate toDate = DateUtils.getZonedDate(0);

        return getAccountDetails(accountId, fromDate, toDate, purchasesSummary, vendorSummary);
    }

}
