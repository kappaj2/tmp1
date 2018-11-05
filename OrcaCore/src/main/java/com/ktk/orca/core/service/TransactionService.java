package com.ktk.orca.core.service;

import com.ktk.orca.core.api.model.TransactionListProcessingResponse;
import com.ktk.orca.core.api.model.TransactionReportStatusResponse;
import com.ktk.orca.core.exceptions.OrcaApiException;
import org.threeten.bp.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public interface TransactionService {

    TransactionListProcessingResponse getTransactions(@NotNull LocalDate fromDate, @NotNull LocalDate toDate, @NotNull String type, @NotNull Integer pageStart, @NotNull Integer pageSize) throws OrcaApiException;

    TransactionReportStatusResponse getTransactionReportStatus(@Size(min = 5) @NotNull String transactionListId) throws OrcaApiException;
}
