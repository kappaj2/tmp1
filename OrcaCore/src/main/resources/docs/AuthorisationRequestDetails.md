
# AuthorisationRequestDetails

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**dateTime** | **String** | Date-Time notation as defined by RFC 3339, section 5.6, for example, 2017-07-21T17:32:28Z / 2017-07-21T19:32:28+02:00  Values in requests are accepted in any timezone and will be converted to UTC before being processed / stored by MobileMart.  All date-time values in responses are UTC timezone.  |  [optional]
**amount** | **Long** | The retail amount of the transaction in CENTS (eg R45.12 &#x3D; 4512)  |  [optional]
**charges** | [**List&lt;PurchaseTransactionCharges&gt;**](PurchaseTransactionCharges.md) | Charges applied against the transaction |  [optional]
**deviceReference** | [**PurchaseTransactionDeviceReference**](PurchaseTransactionDeviceReference.md) |  |  [optional]
**catalogueProductName** | **String** | Name of the purchased product as obtained from the Catalogue |  [optional]
**vendorMessage** | **String** | Message from the vendor to be included on the receipt / voucher |  [optional]
**id** | **String** |  |  [optional]
**clientTransactionReference** | **String** | Purchase reference as supplied by client system |  [optional]
**catalogueProductID** | **String** | The ID of the purchased product as obtained from the Catalogue |  [optional]
**vendorName** | **String** | Vendor name, e.g. for airtime is the provider, for electricity the municipality name, etc. |  [optional]
**statusMessage** | **String** | Descriptive message of status code |  [optional]
**statusCode** | **Integer** | Status code of response : 0 &#x3D;&#x3D; success |  [optional]



