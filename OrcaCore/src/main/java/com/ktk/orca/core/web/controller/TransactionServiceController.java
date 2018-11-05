package com.ktk.orca.core.web.controller;

import com.ktk.orca.core.api.model.TransactionListProcessingResponse;
import com.ktk.orca.core.api.model.TransactionReportStatusResponse;
import com.ktk.orca.core.exceptions.OrcaApiException;
import com.ktk.orca.core.service.TransactionService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.threeten.bp.LocalDate;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class TransactionServiceController {

    private TransactionService transactionService;

    public TransactionServiceController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Timed
    @Audited
    @GetMapping("/transaction/get-transactions/{fromDate}/{toDate}/{type}/{pageStart}/{pageSize}")
    public ResponseEntity getTransactions(@PathVariable final LocalDate fromDate,
                                          @PathVariable final LocalDate toDate,
                                          @PathVariable final String type,
                                          @PathVariable final Integer pageStart,
                                          @PathVariable final Integer pageSize) {
        try {

            TransactionListProcessingResponse response = transactionService.getTransactions(fromDate, toDate, type, pageStart, pageSize);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (OrcaApiException mapie) {
            return new ResponseEntity<>(mapie.getApiError(), HttpStatus.valueOf(mapie.getApiError().getStatusCode()));
        }
    }

    @Timed
    @Audited
    @GetMapping("/transaction/get-transaction-report/{transactionListId}")
    public ResponseEntity getTransactionReportStatus(@PathVariable final String transactionListId) {
        try {

            TransactionReportStatusResponse response = transactionService.getTransactionReportStatus(transactionListId);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (OrcaApiException mapie) {
            return new ResponseEntity<>(mapie.getApiError(), HttpStatus.valueOf(mapie.getApiError().getStatusCode()));
        }
    }
}
