package com.ktk.orca.core.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktk.orca.core.api.client.AccountApi;
import com.ktk.orca.core.api.invoker.ApiClient;
import com.ktk.orca.core.api.model.Account;
import com.ktk.orca.core.api.model.AccountDetailsResponse;
import com.ktk.orca.core.config.ApplicationProperties;
import com.ktk.orca.core.exceptions.OrcaApiException;
import com.ktk.orca.core.utilities.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;
import org.threeten.bp.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

    private Fixture fixture;
    private ObjectMapper objectMapper;


    @Before
    public void setup() {
        fixture = new Fixture();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAccountDetails() throws Exception {

        String txRef = "TransactionRef123";

        fixture.givenAccountId("acct1");
        fixture.givenFromDate(DateUtils.getZonedDate(10));
        fixture.givenToDate(DateUtils.getZonedDate(2));
        fixture.givenPurchaseSummary(true);
        fixture.givenVendorSummary(true);
        fixture.givenHttpStatusResponse(HttpStatus.OK);
        fixture.whenGetAccountDetailsCalled();
        fixture.thenTransactionReferenceShouldBe(txRef);
    }

    @Test
    public void testGetAccountDetailsWhenHttpError() throws Exception {

        String txRef = "TransactionRef123";

        fixture.givenAccountId("acct1");
        fixture.givenFromDate(DateUtils.getZonedDate(10));
        fixture.givenToDate(DateUtils.getZonedDate(2));
        fixture.givenPurchaseSummary(true);
        fixture.givenVendorSummary(true);
        fixture.givenHttpStatusResponse(HttpStatus.BAD_GATEWAY);
        fixture.whenHttpErrorThrown();
      //  fixture.thenTransactionReferenceShouldBe(txRef);
    }

    private class Fixture {

        private String accountId;
        private LocalDate fromDate;
        private LocalDate toDate;
        private Boolean purchasesSummary;
        private Boolean vendorSummary;
        //  Set to default success - will test negative with explicit setting.
        private HttpStatus httpStatus = HttpStatus.OK;
        private AccountDetailsResponse accountDetailsResponse;
        private AccountDetailsResponse accountResponse;

        @Mock
        private AccountApi accountApi;

        @Mock
        private ApiClient apiClient;

        @Mock
        private BaseServiceUtil baseServiceUtil;
        @Mock
        private ApplicationProperties props;
        @Mock
        private MessageSource messageSource;

        @InjectMocks
        private AccountServiceImpl accountService;//= new AccountServiceImpl(accountApi, props, baseServiceUtil, messageSource);


        public Fixture() {
            MockitoAnnotations.initMocks(this);
        }

        public void givenAccountId(String accountId) {
            this.accountId = accountId;
        }

        public void givenFromDate(LocalDate fromDate) {
            this.fromDate = fromDate;
        }

        public void givenToDate(LocalDate toDate) {
            this.toDate = toDate;
        }

        public void givenPurchaseSummary(Boolean purchasesSummary) {
            this.purchasesSummary = purchasesSummary;
        }

        public void givenVendorSummary(Boolean vendorSummary) {
            this.vendorSummary = vendorSummary;
        }

        public void givenHttpStatusResponse(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
        }

        public void whenGetAccountDetailsCalled() throws Exception {

            accountDetailsResponse = new AccountDetailsResponse();

            Account accountSummary = new Account();

            accountDetailsResponse.setTransactionRef("TransactionRef123");
            accountDetailsResponse.setAccountSummary(accountSummary);

            /*
            2. A spy is stubbed using when(spy.foo()).then() syntax. It is safer to stub spies -
                - with doReturn|Throw() family of methods. More in javadocs for Mockito.spy() method.
             */
            when(accountApi.getAccount(accountId, fromDate, toDate, purchasesSummary, vendorSummary))
                    .thenReturn(accountDetailsResponse);

            when(baseServiceUtil.createNewObjectNode())
                    .thenReturn(objectMapper.createObjectNode());

            when(accountApi.getApiClient())
                    .thenReturn(apiClient);

            when(apiClient.getStatusCode())
                    .thenReturn(httpStatus);

            accountResponse = accountService.getAccountDetails(accountId, fromDate, toDate, purchasesSummary, vendorSummary);
        }

        public void whenHttpErrorThrown() throws Exception{

            RestClientException rce = new RestClientException("Error processing request", null);

            when(accountApi.getAccount(accountId, fromDate, toDate, purchasesSummary, vendorSummary))
                    .thenThrow(rce);

            when(baseServiceUtil.createNewObjectNode())
                    .thenReturn(objectMapper.createObjectNode());

            when(accountApi.getApiClient())
                    .thenReturn(apiClient);

            when(apiClient.getStatusCode())
                    .thenReturn(httpStatus);

            assertThatThrownBy(() ->  accountService.getAccountDetails(accountId, fromDate, toDate, purchasesSummary, vendorSummary))
                    .isInstanceOf(OrcaApiException.class)
                    .hasCauseExactlyInstanceOf(RestClientException.class);
        }

        public void thenTransactionReferenceShouldBe(String transRef) {
            assertEquals(transRef, accountResponse.getTransactionRef());
        }

    }
}