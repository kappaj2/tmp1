package com.ktk.orca.core.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktk.orca.core.api.client.TransactionListApi;
import com.ktk.orca.core.api.invoker.ApiClient;
import com.ktk.orca.core.api.model.TransactionListProcessingResponse;
import com.ktk.orca.core.api.model.TransactionReportStatusResponse;
import com.ktk.orca.core.config.ApplicationProperties;
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
import org.threeten.bp.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {

    private Fixture fixture;
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        fixture = new Fixture();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetTransactions() throws Exception {

        String transactionRef = "transref1";

        TransactionListProcessingResponse transactionListProcessingResponse = new TransactionListProcessingResponse();
        transactionListProcessingResponse.setTransactionRef(transactionRef);

        fixture.givenTransactionRef(transactionRef);
        fixture.givenTxType("transtype1");
        fixture.givenFromDate(DateUtils.getZonedDate(10));
        fixture.givenToDate(DateUtils.getZonedDate(2));
        fixture.whenGetTransactions();
        fixture.thenTransactionListProcessingResponse(transactionListProcessingResponse);

    }

    @Test
    public void testGetTransactionReportStatus() throws Exception {

        String transactionRef = "transref2";

        TransactionReportStatusResponse transactionReportStatusResponse = new TransactionReportStatusResponse();
        transactionReportStatusResponse.setTransactionRef(transactionRef);

        fixture.givenTransactionRef(transactionRef);
        fixture.givenTxType("transtype2");
        fixture.givenFromDate(DateUtils.getZonedDate(10));
        fixture.givenToDate(DateUtils.getZonedDate(2));
        fixture.whenGetTransactionReportStatus();
        fixture.thenTransactionReportStatusResponse(transactionReportStatusResponse);
    }

    private class Fixture {

        private TransactionListProcessingResponse transactionListProcessingResponse;
        private TransactionListProcessingResponse listProcessingResponse;
        private TransactionReportStatusResponse transactionReportStatusResponse;
        private TransactionReportStatusResponse reportStatusResponse;

        private String transactionRef;
        private String transactionListId;
        private String txType;
        private Integer pageSize = Integer.parseInt("24");
        private Integer pageStart = Integer.parseInt("1");
        private LocalDate fromDate;
        private LocalDate toDate;
        //  Set to default success - will test negative with explicit setting.
        private HttpStatus httpStatus = HttpStatus.OK;

        @Mock
        private ApiClient apiClient;
        @Mock
        private TransactionListApi transactionListApi;
        @Mock
        private BaseServiceUtil baseServiceUtil;
        @Mock
        private ApplicationProperties props;
        @Mock
        private MessageSource messageSource;

        @InjectMocks
        private TransactionServiceImpl transactionService;

        public Fixture() {
            MockitoAnnotations.initMocks(this);
        }

        public void givenTransactionRef(String transactionRef) {
            this.transactionRef = transactionRef;
        }

        public void givenFromDate(LocalDate fromDate) {
            this.fromDate = fromDate;
        }

        public void givenToDate(LocalDate toDate) {
            this.toDate = toDate;
        }

        public void givenTransactionListId(String transactionListId) {
            this.transactionListId = transactionListId;
        }

        public void givenTxType(String txType) {
            this.txType = txType;
        }

        public void whenGetTransactions() throws Exception{

            transactionListProcessingResponse = new TransactionListProcessingResponse();
            transactionListProcessingResponse.setTransactionRef(transactionRef);

            when(transactionListApi.getTransactions(fromDate, toDate, txType, pageStart, pageSize))
                    .thenReturn(transactionListProcessingResponse);

            when(baseServiceUtil.createNewObjectNode())
                    .thenReturn(objectMapper.createObjectNode());

            when(transactionListApi.getApiClient())
                    .thenReturn(apiClient);

            when(apiClient.getStatusCode())
                    .thenReturn(httpStatus);

            listProcessingResponse = transactionService.getTransactions(fromDate, toDate, txType, pageStart, pageSize);
        }

        public void whenGetTransactionReportStatus() throws Exception{

            transactionReportStatusResponse = new TransactionReportStatusResponse();
            transactionReportStatusResponse.setTransactionRef(transactionRef);

            TransactionReportStatusResponse transactionReportStatusResponse = new TransactionReportStatusResponse();
            transactionReportStatusResponse.setTransactionRef(transactionRef);

            when(transactionListApi.getTransactionReportStatus(transactionListId))
                    .thenReturn(transactionReportStatusResponse);

            when(baseServiceUtil.createNewObjectNode())
                    .thenReturn(objectMapper.createObjectNode());

            when(transactionListApi.getApiClient())
                    .thenReturn(apiClient);

            when(apiClient.getStatusCode())
                    .thenReturn(httpStatus);

            reportStatusResponse = transactionService.getTransactionReportStatus(transactionListId);
        }

        public void thenTransactionListProcessingResponse(TransactionListProcessingResponse transactionListProcessingResponse) {
            assertEquals(transactionListProcessingResponse.getTransactionRef(), listProcessingResponse.getTransactionRef());
        }

        public void thenTransactionReportStatusResponse(TransactionReportStatusResponse transactionReportStatusResponse) {
            assertEquals(transactionReportStatusResponse.getTransactionRef(), reportStatusResponse.getTransactionRef());
        }
    }
}