package com.ktk.orca.core.service;

import com.ktk.orca.core.api.model.AccountFundsResponse;
import com.ktk.orca.core.api.model.AccountFundsTransaction;
import com.ktk.orca.core.api.model.AccountFundsTransactionResponse;
import com.ktk.orca.core.exceptions.OrcaApiException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public interface FundsManagementService {

    AccountFundsResponse getAccountFunds(@Size(min = 5) @NotNull String accountId) throws OrcaApiException;

    AccountFundsTransactionResponse notifyAccountFundsTransaction(@Size(min = 5) @NotNull String accountId, @NotNull AccountFundsTransaction accountFundsTransaction) throws OrcaApiException;
}
