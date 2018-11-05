package com.ktk.orca.core.web.controller;

import com.ktk.orca.core.api.model.ProductCatalogResponse;
import com.ktk.orca.core.api.model.ProductListResponse;
import com.ktk.orca.core.exceptions.OrcaApiException;
import com.ktk.orca.core.service.ProductService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ProductServiceController {

    private ProductService productService;

    public ProductServiceController(ProductService productService) {
        this.productService = productService;
    }

    @Timed
    @Audited
    @GetMapping("/product/product-catalog/{categories}/{vendors}")
    public ResponseEntity getProductCatalog(@PathVariable final List<String> categories,
                                            @PathVariable final List<String> vendors) {
        try {
            ProductCatalogResponse response = productService.getProductCatalog(categories, vendors);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (OrcaApiException mapie) {
            return new ResponseEntity<>(mapie.getApiError(), HttpStatus.valueOf(mapie.getApiError().getStatusCode()));
        }
    }

    @Timed
    @Audited
    @GetMapping("/product/product-list/{categories}/{vendors}/{pageStart}/{pageSize}")
    public ResponseEntity getProductList(@PathVariable final List<String> categories,
                                         @PathVariable final List<String> vendors,
                                         @PathVariable final Integer pageStart,
                                         @PathVariable final Integer pageSize) {
        try {
            ProductListResponse response = productService.getProductList(categories, vendors, pageStart, pageSize);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (OrcaApiException mapie) {
            return new ResponseEntity<>(mapie.getApiError(), HttpStatus.valueOf(mapie.getApiError().getStatusCode()));
        }
    }
}
