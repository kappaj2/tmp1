package com.ktk.orca.core.service;

import com.ktk.orca.core.api.model.*;
import com.ktk.orca.core.exceptions.OrcaApiException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public interface PurchaseService {

    PurchaseResponse createPurchase(@NotNull PurchaseRequest purchaseRequest) throws OrcaApiException;

    PurchaseDetailsResponse getPurchaseDetails(@Size(min = 5) @NotNull String purchaseId) throws OrcaApiException;

    PurchaseResponse authPurchase(@Size(min = 5) @NotNull String purchaseId, @NotNull PurchaseAuthorisationRequest purchaseAuthorisationRequest) throws OrcaApiException;

    PurchaseResponse reprintReceipt(@Size(min = 5) @NotNull String purchaseId, @NotNull PurchaseReprintRequest purchaseReprintRequest) throws OrcaApiException;

}
