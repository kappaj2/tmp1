package com.ktk.orca.core.service;


import com.ktk.orca.core.api.model.ProductCatalogResponse;
import com.ktk.orca.core.api.model.ProductListResponse;
import com.ktk.orca.core.exceptions.OrcaApiException;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ProductService {

    ProductCatalogResponse getProductCatalog(@NotNull List<String> categories, @NotNull List<String> vendors) throws OrcaApiException;

    ProductListResponse getProductList(@NotNull List<String> categories, @NotNull List<String> vendors, @NotNull Integer pageStart, @NotNull Integer pageSize) throws OrcaApiException;

}
