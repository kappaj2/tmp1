package com.ktk.orca.core.web.controller;

import com.ktk.orca.core.api.model.AccountDetailsResponse;
import com.ktk.orca.core.exceptions.OrcaApiException;
import com.ktk.orca.core.service.AccountService;
import io.micrometer.core.annotation.Timed;
import org.hibernate.envers.Audited;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.threeten.bp.LocalDate;

@RestController
@RequestMapping("/api/v1")
public class AccountServiceControlller {

    private AccountService accountService;

    public AccountServiceControlller(AccountService accountService) {
        this.accountService = accountService;
    }

    @Timed
    @Audited
    @GetMapping(value = "/account/account-details/{accountId}/{fromDate}/{toDate}/{purchaseSummary}/{vendorSummary}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAccountService(@PathVariable final String accountId,
                                            @PathVariable LocalDate fromDate,
                                            @PathVariable LocalDate toDate,
                                            @PathVariable final Boolean purchaseSummary,
                                            @PathVariable final Boolean vendorSummary) {
        try {

            AccountDetailsResponse response = accountService.getAccountDetails(accountId, fromDate, toDate, purchaseSummary, vendorSummary);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (OrcaApiException mapie) {
            return new ResponseEntity<>(mapie.getApiError(), HttpStatus.valueOf(mapie.getApiError().getStatusCode()));
        }
    }

    @Timed
    @Audited
    @GetMapping("/account/month-account/{accountId}/{purchaseSummary}/{vendorSummary}")
    public ResponseEntity getCurrentMonthAccountService(@PathVariable final String accountId,
                                                        @PathVariable final Boolean purchaseSummary,
                                                        @PathVariable final Boolean vendorSummary) {

        try {

            AccountDetailsResponse response = accountService.getCurrentMonthAccountDetails(accountId, purchaseSummary, vendorSummary);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (OrcaApiException mapie) {
            return new ResponseEntity<>(mapie.getApiError(), HttpStatus.valueOf(mapie.getApiError().getStatusCode()));
        }
    }
}
