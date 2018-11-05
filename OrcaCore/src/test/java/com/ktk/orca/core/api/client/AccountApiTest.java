package com.ktk.orca.core.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktk.orca.core.IntegrationTest;
import com.ktk.orca.core.api.invoker.ApiClient;
import com.ktk.orca.core.api.model.AccountDetailsResponse;
import com.ktk.orca.core.config.ApplicationProperties;
import com.ktk.orca.core.utilities.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.threeten.bp.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * API tests for AccountApi
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
@Category(IntegrationTest.class)
@Ignore
public class AccountApiTest {

    @Autowired
    private ApplicationProperties props;

    @Autowired
    private AccountApi accountApi;

    @Autowired
    @Qualifier("jsonObjectMapper")
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        String propFile = props.getPropFile();
    }

    /**
     * Account Details
     * <p>
     * Return account details. Default - returns account summary with purchases totals by product category for the calendar month to date .   Additional details are requested via the following query parameters   * **purchases** - include last 30 product purchase transactions   * **vendorBreakdown** - include per vendor breakdown in product category totals   * **periodStart, periodEnd** - Select a custom period to aggregate purchases by specifying start and end dates
     *
     * @throws RestClientException if the Api call fails
     */
    @Test
    public void getAccountTest() {

        String id = "1";

        LocalDate periodStart = DateUtils.getZonedDate(0);
        LocalDate periodEnd = DateUtils.getZonedDate(5);
        Boolean purchasesSummary = false;
        Boolean vendorSummary = false;

        ApiClient apiClient = accountApi.getApiClient();
        try {
            accountApi.getApiClient().setDebugging(true);
            accountApi.getApiClient().setBasePath(props.getOrcaApi().getBaseUri());

            AccountDetailsResponse response = accountApi.getAccount(id, periodStart, periodEnd, purchasesSummary, vendorSummary);

            HttpStatus responseCode = accountApi.getApiClient().getStatusCode();
            int respCode = responseCode.value();

            assertEquals(response.getTransactionRef(), "3dd4fa-2899-4429-b818-d34fe8df5d");
        } catch (RestClientException apie) {
            log.error("Error calling : " + apie.getMessage(), apie);
            HttpStatus responseCode = apiClient.getStatusCode();

            fail("Error occurred");
        }
    }

}
