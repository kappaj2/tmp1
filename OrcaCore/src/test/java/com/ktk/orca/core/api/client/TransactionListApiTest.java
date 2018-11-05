package com.ktk.orca.core.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktk.orca.core.IntegrationTest;
import com.ktk.orca.core.api.model.TransactionListProcessingResponse;
import com.ktk.orca.core.api.model.TransactionReportStatusResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZonedDateTime;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * API tests for TransactionListApi
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
@Category(IntegrationTest.class)
@Ignore
public class TransactionListApiTest {

    @Autowired
    private TransactionListApi transactionListApi;

    @Autowired
    @Qualifier("jsonObjectMapper")
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
    }

    /**
     * Transaction Report Status
     * <p>
     * Check status of transaction report / listing previously requested
     *
     * @throws RestClientException if the Api call fails
     */
    @Test
    public void getTransactionReportStatusTest() {
        String id = "1";
        try {
            TransactionReportStatusResponse response = transactionListApi.getTransactionReportStatus(id);
            String reportURL = response.getReportURL();
            TransactionReportStatusResponse.StatusEnum responseStatus = response.getStatus();
            String responseTransactionRef = response.getTransactionRef();

            assertEquals(TransactionReportStatusResponse.StatusEnum.AVAILABLE, responseStatus);
            assertEquals("HKJH.876.MNB", responseTransactionRef);
        } catch (RestClientException apie) {
            fail("Error occurred");
        }
    }

    /**
     * Transaction List
     * <p>
     * Transactions for the period. Default period - month to date
     *
     * @throws RestClientException if the Api call fails
     */
    @Test
    public void getTransactionsTest() {

        Calendar calStart = Calendar.getInstance();
        calStart.add(Calendar.DATE, -5);

        ZonedDateTime zdtStart = DateTimeUtils.toZonedDateTime(calStart);

        ZonedDateTime zdtNow = DateTimeUtils.toZonedDateTime(Calendar.getInstance());

        LocalDate periodStart = zdtStart.toLocalDate();
        LocalDate periodEnd = zdtNow.toLocalDate();

        Integer pageStart = 1;
        Integer pageSize = 40;
        String type = null;

        try {
            //LocalDate periodStart, LocalDate periodEnd, String type, Integer pageStart, Integer pageSize
            TransactionListProcessingResponse response = transactionListApi.getTransactions(periodStart, periodEnd, type, pageStart, pageSize);

            String responseListURL = response.getListURL();
            String responseTransactionListId = response.getTransactionListId();
            String responseTransactionRef = response.getTransactionRef();

            assertEquals("LKJJ-J86H-48GHSM-KL33", responseTransactionListId);
            assertEquals("KJH.876.NBV", responseTransactionRef);
        } catch (RestClientException apie) {
            fail("Error occurred");
        }
    }

}
