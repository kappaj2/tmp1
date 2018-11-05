package com.ktk.orca.core.api.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktk.orca.core.IntegrationTest;
import com.ktk.orca.core.api.model.AccountFundsResponse;
import com.ktk.orca.core.api.model.AccountFundsTransaction;
import com.ktk.orca.core.api.model.AccountFundsTransactionResponse;
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
import org.threeten.bp.LocalDate;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * API tests for FundsManagementApi
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
@Category(IntegrationTest.class)
@Ignore
public class FundsManagementApiTest {

    @Autowired
    private FundsManagementApi fundsManagementApi;

    @Autowired
    @Qualifier("jsonObjectMapper")
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Account Funds Summary
     * <p>
     * Get funds balances for account
     *
     * @throws RestClientException if the Api call fails
     */
    @Test
    public void getAccountFundsTest() {
        String id = "1";

        try {
            AccountFundsResponse response = fundsManagementApi.getAccountFunds(id);
            assertEquals("LKJ.6540.NFH", response.getTransactionRef());
            assertEquals("ZAR", response.getAccountSummary().getAccountCurrency());
        } catch (RestClientException apie) {
            fail("Error occurred");
        }
    /*
    result = {Account@13502} "class Account {\n    notificationBalance: 3000000\n    clientName: Stubbed Client\n    minimumBalance: 2000000\n    accountCurrency: ZAR\n    creditLimit: -5000000\n    id: 3dd4fa-2899-4429-b818-d34fe8df5d\n    currentbalance: 45000000\n}"
 notificationBalance = {BigDecimal@13505} "3000000"
 clientName = "Stubbed Client"
 minimumBalance = {BigDecimal@13507} "2000000"
 accountCurrency = "ZAR"
 creditLimit = {BigDecimal@13509} "-5000000"
 id = "3dd4fa-2899-4429-b818-d34fe8df5d"
 currentbalance = {BigDecimal@13511} "45000000"
     */
    }

    /**
     * Funds Transaction Notice
     * <p>
     * Create funds deposit or withdrawal notification
     *
     * @throws RestClientException if the Api call fails
     */
    @Test
    public void notifyAccountFundsTransactionTest() {
        String id = "1";
        AccountFundsTransaction accountFundsTransaction = new AccountFundsTransaction();
        accountFundsTransaction.setAmount(new BigDecimal("1000"));
        accountFundsTransaction.setCurrency("ZAR");
        accountFundsTransaction.setDate(LocalDate.now());
        accountFundsTransaction.setTransactionReference("TestTxRef1");
        accountFundsTransaction.setInstructions("TestInstruction");
        accountFundsTransaction.setTransactionType(AccountFundsTransaction.TransactionTypeEnum.DEPOSIT);

        try {
            AccountFundsTransactionResponse response = fundsManagementApi.notifyAccountFundsTransaction(id, accountFundsTransaction);
            String txRef = response.getTransactionRef();
            response.getAccountId();

        } catch (RestClientException apie) {
            fail("Error occurred");
        }
    }

}
