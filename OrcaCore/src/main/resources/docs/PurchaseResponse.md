
# PurchaseResponse

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**transactionDetails** | [**PurchaseTransaction**](PurchaseTransaction.md) |  |  [optional]
**transactionStatus** | [**TransactionStatusEnum**](#TransactionStatusEnum) | * FULLY_PROCESSED - Purchase processed successfully with the Vendor * AUTH_REQUIRED - Purchase accepted for processing, with additional authorisation step is required to complete the purchase * ACCEPTED - Where a specific product allows for delayed processing, the purchase instruction is acepted for later processing  |  [optional]
**authorisationRequest** | [**AuthorisationRequestDetails**](AuthorisationRequestDetails.md) |  |  [optional]
**transactionRef** | **String** | MobileMart transaction reference number. Uniquely allocated per client initiated request. |  [optional]


<a name="TransactionStatusEnum"></a>
## Enum: TransactionStatusEnum
Name | Value
---- | -----
ACCEPTED | &quot;ACCEPTED&quot;
AUTH_REQUIRED | &quot;AUTH_REQUIRED&quot;
FULLY_PROCESSED | &quot;FULLY_PROCESSED&quot;



