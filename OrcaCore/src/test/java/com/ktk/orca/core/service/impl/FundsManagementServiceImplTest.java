package com.ktk.orca.core.service.impl;

import com.ktk.orca.core.api.client.FundsManagementApi;
import com.ktk.orca.core.api.model.Account;
import com.ktk.orca.core.api.model.AccountFundsResponse;
import com.ktk.orca.core.api.model.AccountFundsTransaction;
import com.ktk.orca.core.api.model.AccountFundsTransactionResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FundsManagementServiceImplTest {

    private Fixture fixture;

    @Before
    public void setup() {
        fixture = new Fixture();
    }

    @Test
    public void testGetAccountFunds() throws Exception{
        String transactionRef = "tx1234567890";
        String accountId = "acc1";
        BigDecimal creditLimit = new BigDecimal("12.22");

        fixture.givenCreditLimit(creditLimit);
        fixture.givenAccountIdIs(accountId);
        fixture.givenTransactionRefIs(transactionRef);
        fixture.whenGetAccountFunds();
        fixture.thenAccountFundsTransactionRefShouldBe(transactionRef);
        fixture.thenAccountResponseCreditLimitShouldBe(creditLimit);
    }

    @Test
    public void testNotifyFundsTransaction() throws Exception{
        String transactionRef = "tx123456789";
        String accountId = "acc2";
        AccountFundsTransaction accountFundsTransaction = new AccountFundsTransaction();
        accountFundsTransaction.setAmount(new BigDecimal(10.11));
        accountFundsTransaction.setTransactionReference(transactionRef);

        fixture.givenAccountIdIs(accountId);
        fixture.givenTransactionRefIs(transactionRef);
        fixture.givenAccountFundsTransactionIs(accountFundsTransaction);
        fixture.whenNotifyAccountFundsTransaction();
        fixture.thenNotificationTransactionRefShouldBe(transactionRef);
    }

    private class Fixture {

        private AccountFundsResponse accountFundsResponse;
        private AccountFundsResponse accountResponse;
        private AccountFundsTransactionResponse accountFundsTransactionResponse;
        private AccountFundsTransactionResponse accountTransactionResponse;

        private AccountFundsTransaction accountFundsTransaction;
        private String accountId;
        private String transactionRef;
        private BigDecimal creditLimit;

        @Mock
        private FundsManagementApi fundsManagementApi;

        @InjectMocks
        private FundsManagementServiceImpl fundsManagementService;

        public Fixture() {
            MockitoAnnotations.initMocks(this);
        }

        public void givenAccountIdIs(String accountId){
            this.accountId = accountId;
        }

        public void givenAccountFundsTransactionIs(AccountFundsTransaction accountFundsTransaction){
            this.accountFundsTransaction = accountFundsTransaction;
        }

        public void givenTransactionRefIs(String transactionRef){
            this.transactionRef = transactionRef;
        }

        public void givenCreditLimit(BigDecimal creditLimit){
            this.creditLimit = creditLimit;
        }

        public void whenGetAccountFunds(){
            accountFundsResponse = new AccountFundsResponse();
            Account accountSummary = new Account();
            accountSummary.setCreditLimit(creditLimit);

            accountFundsResponse.setTransactionRef(transactionRef);
            accountFundsResponse.setAccountSummary(accountSummary);

            when(fundsManagementApi.getAccountFunds(accountId))
                    .thenReturn(accountFundsResponse);

            accountResponse = fundsManagementApi.getAccountFunds(accountId);
        }

        public void whenNotifyAccountFundsTransaction(){
            accountFundsTransactionResponse = new AccountFundsTransactionResponse();
            accountFundsTransactionResponse.setTransactionRef(transactionRef);

            when(fundsManagementApi.notifyAccountFundsTransaction(accountId, accountFundsTransaction))
                    .thenReturn(accountFundsTransactionResponse);

            accountTransactionResponse = fundsManagementApi.notifyAccountFundsTransaction(accountId, accountFundsTransaction);
        }

        public void thenAccountFundsTransactionRefShouldBe(String transRef){
            assertEquals(transRef, accountResponse.getTransactionRef());
        }

        public void thenAccountResponseCreditLimitShouldBe(BigDecimal creditLimit){
            assertTrue(creditLimit.equals(accountResponse.getAccountSummary().getCreditLimit()));
        }

        public void thenNotificationTransactionRefShouldBe(String transRef){
            assertEquals(transRef, accountTransactionResponse.getTransactionRef());
        }

    }
}