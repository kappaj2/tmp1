package com.ktk.orca.core.web.controller;

import com.ktk.orca.core.api.model.*;
import com.ktk.orca.core.exceptions.OrcaApiException;
import com.ktk.orca.core.service.PurchaseService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class PurchaseServiceController {

    private PurchaseService purchaseService;

    public PurchaseServiceController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Timed
    @Audited
    @PostMapping("/purchase/create-purchase")
    public ResponseEntity createPurchase(@RequestBody final PurchaseRequest purchaseRequest) {
        try {

            PurchaseResponse response = purchaseService.createPurchase(purchaseRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (OrcaApiException mapie) {
            return new ResponseEntity<>(mapie.getApiError(), HttpStatus.valueOf(mapie.getApiError().getStatusCode()));
        }
    }

    @Timed
    @Audited
    @GetMapping("/purchase/purchase-details/{purchaseId}")
    public ResponseEntity getPurchaseDetails(@PathVariable final String purchaseId) {
        try {

            PurchaseDetailsResponse response = purchaseService.getPurchaseDetails(purchaseId);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (OrcaApiException mapie) {
            return new ResponseEntity<>(mapie.getApiError(), HttpStatus.valueOf(mapie.getApiError().getStatusCode()));
        }
    }

    @Timed
    @Audited
    @PutMapping("/purchase/auth-purchase/{purchaseId}")
    public ResponseEntity authPurchase(@PathVariable final String purchaseId,
                                       @RequestBody final PurchaseAuthorisationRequest purchaseAuthorisationRequest) {
        try {

            PurchaseResponse response = purchaseService.authPurchase(purchaseId, purchaseAuthorisationRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (OrcaApiException mapie) {
            return new ResponseEntity<>(mapie.getApiError(), HttpStatus.valueOf(mapie.getApiError().getStatusCode()));
        }
    }

    @Timed
    @Audited
    @PostMapping("/purchase/reprint-purchase/{purchaseId}")
    public ResponseEntity createPurchase(@PathVariable final String purchaseId,
                                         @RequestBody final PurchaseReprintRequest purchaseReprintRequest) {
        try {

            PurchaseResponse response = purchaseService.reprintReceipt(purchaseId, purchaseReprintRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (OrcaApiException mapie) {
            return new ResponseEntity<>(mapie.getApiError(), HttpStatus.valueOf(mapie.getApiError().getStatusCode()));
        }
    }
}
