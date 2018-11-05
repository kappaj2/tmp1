package com.ktk.orca.core.service;

import com.ktk.orca.core.api.model.ProductVendorsResponse;
import com.ktk.orca.core.exceptions.OrcaApiException;

import javax.validation.constraints.NotNull;

public interface VendorService {

    ProductVendorsResponse getVendorList(@NotNull Boolean includeProducts) throws OrcaApiException;
}
