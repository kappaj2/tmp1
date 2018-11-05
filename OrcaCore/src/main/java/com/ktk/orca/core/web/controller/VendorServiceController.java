package com.ktk.orca.core.web.controller;


import com.ktk.orca.core.api.model.ProductVendorsResponse;
import com.ktk.orca.core.exceptions.OrcaApiException;
import com.ktk.orca.core.service.VendorService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class VendorServiceController {

    private VendorService vendorService;

    public VendorServiceController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @Timed
    @Audited
    @GetMapping("/vendor/get-vendors/{includeProducts}")
    public ResponseEntity getVendorList(@PathVariable final Boolean includeProducts) {
        try {

            ProductVendorsResponse response = vendorService.getVendorList(includeProducts);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (OrcaApiException mapie) {
            return new ResponseEntity<>(mapie.getApiError(), HttpStatus.valueOf(mapie.getApiError().getStatusCode()));
        }
    }
}
