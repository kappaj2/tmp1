package com.ktk.orca.core.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktk.orca.core.api.client.PurchasesApi;
import com.ktk.orca.core.api.invoker.ApiClient;
import com.ktk.orca.core.api.model.*;
import com.ktk.orca.core.config.ApplicationProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseServiceImplTest {

    private Fixture fixture;
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        fixture = new Fixture();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testAuthPurchase() throws Exception {

        String transactionRef = "txRef1";
        String purchaseId = "purchase11";

        PurchaseResponse purchaseResponse = new PurchaseResponse();
        purchaseResponse.setTransactionRef(transactionRef);

        PurchaseAuthorisationRequest purchaseAuthorisationRequest = new PurchaseAuthorisationRequest();

        fixture.givenPurchaseAuthorisationRequest(purchaseAuthorisationRequest);
        fixture.givenTransactionRef(transactionRef);
        fixture.givenPurchaseId(purchaseId);
        fixture.whenAuthPurchase();
        fixture.thenPurchaseResponseIs(purchaseResponse);
    }

    @Test
    public void testCreatePurchase() throws Exception {
        String transactionRef = "txRef12";
        String purchaseId = "purchase112";

        PurchaseResponse purchaseResponse = new PurchaseResponse();
        purchaseResponse.setTransactionRef(transactionRef);

        PurchaseRequest purchaseRequest = new PurchaseRequest();

        fixture.givenPurchaseId(purchaseId);
        fixture.givenTransactionRef(transactionRef);
        fixture.givenPurchaseRequest(purchaseRequest);
        fixture.whenCreatePurchase();
        fixture.thenPurchaseResponseIs(purchaseResponse);

    }

    @Test
    public void testGetPurchase() throws Exception {
        String transactionRef = "txRef13";
        String purchaseId = "purchase113";

        PurchaseDetailsResponse purchaseDetailsResponse = new PurchaseDetailsResponse();
        PurchaseResponse purchaseResponse = new PurchaseResponse();
        purchaseResponse.setTransactionRef(transactionRef);

        purchaseDetailsResponse.setPurchaseResponse(purchaseResponse);

        fixture.givenPurchaseId(purchaseId);
        fixture.givenTransactionRef(transactionRef);
        fixture.whenGetPurchaseDetails();
        fixture.thenPurchaseDetailsResponse(purchaseDetailsResponse);
    }

    @Test
    public void testReprintReceipt() throws Exception {
        String transactionRef = "txRef14";
        String purchaseId = "purchase114";

        PurchaseReprintRequest purchaseReprintRequest = new PurchaseReprintRequest();
        PurchaseResponse purchaseResponse = new PurchaseResponse();
        purchaseResponse.setTransactionRef(transactionRef);

        fixture.givenPurchaseId(purchaseId);
        fixture.givenTransactionRef(transactionRef);
        fixture.givenPurchaseReprintRequest(purchaseReprintRequest);
        fixture.whenReprintPurchase();
        fixture.thenPurchaseResponseIs(purchaseResponse);
    }

    private class Fixture {

        private PurchaseResponse purchaseResponse;
        private PurchaseResponse purchResponse;
        private PurchaseDetailsResponse purchaseDetailsResponse;
        private PurchaseDetailsResponse purchDetailsResponse;
        private PurchaseRequest purchaseRequest;
        private PurchaseAuthorisationRequest purchaseAuthorisationRequest;
        private PurchaseReprintRequest purchaseReprintRequest;

        //  Set to default success - will test negative with explicit setting.
        private HttpStatus httpStatus = HttpStatus.OK;

        private String purchaseId;
        private String transactionRef;

        @Mock
        private PurchasesApi purchasesApi;
        @Mock
        private ApiClient apiClient;
        @Mock
        private BaseServiceUtil baseServiceUtil;
        @Mock
        private ApplicationProperties props;
        @Mock
        private MessageSource messageSource;

        @InjectMocks
        private PurchaseServiceImpl purchaseService;

        public Fixture() {
            MockitoAnnotations.initMocks(this);
        }

        public void givenPurchaseId(String purchaseId) {
            this.purchaseId = purchaseId;
        }

        public void givenTransactionRef(String transactionRef) {
            this.transactionRef = transactionRef;
        }

        public void givenPurchaseRequest(PurchaseRequest purchaseRequest) {
            this.purchaseRequest = purchaseRequest;
        }

        public void givenPurchaseAuthorisationRequest(PurchaseAuthorisationRequest purchaseAuthorisationRequest) {
            this.purchaseAuthorisationRequest = purchaseAuthorisationRequest;
        }

        public void givenPurchaseReprintRequest(PurchaseReprintRequest purchaseReprintRequest) {
            this.purchaseReprintRequest = purchaseReprintRequest;
        }

        public void whenAuthPurchase() throws Exception {
            purchaseResponse = new PurchaseResponse();
            purchaseResponse.setTransactionRef(transactionRef);
            when(purchasesApi.authPurchase(purchaseId, purchaseAuthorisationRequest))
                    .thenReturn(purchaseResponse);

            when(baseServiceUtil.createNewObjectNode())
                    .thenReturn(objectMapper.createObjectNode());

            when(purchasesApi.getApiClient())
                    .thenReturn(apiClient);

            when(apiClient.getStatusCode())
                    .thenReturn(httpStatus);

            purchResponse = purchaseService.authPurchase(purchaseId, purchaseAuthorisationRequest);
        }

        public void whenCreatePurchase() throws Exception {
            purchaseResponse = new PurchaseResponse();
            purchaseResponse.setTransactionRef(transactionRef);

            when(purchasesApi.createPurchase(purchaseRequest))
                    .thenReturn(purchaseResponse);

            when(baseServiceUtil.createNewObjectNode())
                    .thenReturn(objectMapper.createObjectNode());

            when(purchasesApi.getApiClient())
                    .thenReturn(apiClient);

            when(apiClient.getStatusCode())
                    .thenReturn(httpStatus);

            purchResponse = purchaseService.createPurchase(purchaseRequest);
        }

        public void whenGetPurchaseDetails() throws Exception {
            purchaseDetailsResponse = new PurchaseDetailsResponse();
            PurchaseResponse purchaseResponse = new PurchaseResponse();
            purchaseResponse.setTransactionRef(transactionRef);
            purchaseDetailsResponse.setPurchaseResponse(purchaseResponse);

            when(purchasesApi.getPurchase(purchaseId))
                    .thenReturn(purchaseDetailsResponse);

            when(baseServiceUtil.createNewObjectNode())
                    .thenReturn(objectMapper.createObjectNode());

            when(purchasesApi.getApiClient())
                    .thenReturn(apiClient);

            when(apiClient.getStatusCode())
                    .thenReturn(httpStatus);

            purchDetailsResponse = purchaseService.getPurchaseDetails(purchaseId);
        }

        public void whenReprintPurchase() throws Exception {
            purchaseResponse = new PurchaseResponse();
            purchaseResponse.setTransactionRef(transactionRef);

            when(purchasesApi.reprintReceipt(purchaseId, purchaseReprintRequest))
                    .thenReturn(purchaseResponse);

            when(baseServiceUtil.createNewObjectNode())
                    .thenReturn(objectMapper.createObjectNode());

            when(purchasesApi.getApiClient())
                    .thenReturn(apiClient);

            when(apiClient.getStatusCode())
                    .thenReturn(httpStatus);

            purchResponse = purchaseService.reprintReceipt(purchaseId, purchaseReprintRequest);
        }

        public void thenPurchaseResponseIs(PurchaseResponse purchaseResponse) {
            assertEquals(purchResponse.getTransactionRef(), purchaseResponse.getTransactionRef());
        }

        public void thenPurchaseDetailsResponse(PurchaseDetailsResponse purchaseDetailsResponse) {
            assertEquals(purchaseDetailsResponse.getPurchaseResponse().getTransactionRef(),
                    purchaseDetailsResponse.getPurchaseResponse().getTransactionRef());
        }
    }
}