package com.ktk.orca.core.service;

import com.ktk.orca.core.api.model.ProductCategoryResponse;
import com.ktk.orca.core.exceptions.OrcaApiException;

import javax.validation.constraints.NotNull;

public interface CategoryService {

    ProductCategoryResponse getProductCategoryList(@NotNull Boolean includeVendors, @NotNull Boolean includeProducts) throws OrcaApiException;
}
