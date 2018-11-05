package com.ktk.orca.core.service;

import com.ktk.orca.core.api.model.AccountDetailsResponse;
import com.ktk.orca.core.exceptions.OrcaApiException;
import org.threeten.bp.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public interface AccountService {

    AccountDetailsResponse getAccountDetails(@Size(min = 5) @NotNull String accountId, @NotNull LocalDate fromDate, @NotNull LocalDate toDate, @NotNull Boolean purchasesSummary, @NotNull Boolean vendorSummary) throws OrcaApiException;

    AccountDetailsResponse getCurrentMonthAccountDetails(@Size(min = 5) @NotNull String accountId, @NotNull Boolean purchasesSummary, @NotNull Boolean vendorSummary) throws OrcaApiException;

}
