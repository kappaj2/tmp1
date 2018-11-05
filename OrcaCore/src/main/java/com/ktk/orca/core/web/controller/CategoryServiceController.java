package com.ktk.orca.core.web.controller;

import com.ktk.orca.core.api.model.ProductCategoryResponse;
import com.ktk.orca.core.exceptions.OrcaApiException;
import com.ktk.orca.core.service.CategoryService;
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
public class CategoryServiceController {

    private CategoryService categoryService;

    public CategoryServiceController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Timed
    @Audited
    @GetMapping("/category/product-category-list/{includeVendors}/{includeProducts}")
    public ResponseEntity getProductCategoryList(@PathVariable final Boolean includeVendors,
                                                 @PathVariable final Boolean includeProducts) {

        try {
            ProductCategoryResponse response = categoryService.getProductCategoryList(includeVendors, includeProducts);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (OrcaApiException mapie) {
            return new ResponseEntity<>(mapie.getApiError(), HttpStatus.valueOf(mapie.getApiError().getStatusCode()));
        }

    }
}
