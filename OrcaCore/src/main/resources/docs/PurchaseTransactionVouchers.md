
# PurchaseTransactionVouchers

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**tarrifBlocks** | [**List&lt;PurchaseTransactionTarrifBlocks&gt;**](PurchaseTransactionTarrifBlocks.md) |  |  [optional]
**amount** | **Integer** | The retail amount of the transaction in CENTS (eg R45.12 &#x3D; 4512)  |  [optional]
**voucherSeq** | **Integer** |  |  [optional]
**voucherReferenceNumber** | **String** | Unique PIN/Code for this product. Typically represents the number to be entered on the target device to redeem the purchased product   For example  * electricity - the reference number to be entered into the meter * airtime - the activation code to be entered on the cellphone  |  [optional]
**voucherType** | **String** |  |  [optional]
**name** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**currency** | **String** | ISO 3 character currency code. Default - ZAR (South African Rand) |  [optional]
**receipt** | [**TransactionReceipt**](TransactionReceipt.md) |  |  [optional]
**expiry** | **String** | Date-Time notation as defined by RFC 3339, section 5.6, for example, 2017-07-21T17:32:28Z / 2017-07-21T19:32:28+02:00  Values in requests are accepted in any timezone and will be converted to UTC before being processed / stored by MobileMart.  All date-time values in responses are UTC timezone.  |  [optional]
**taxAmount** | [**BigDecimal**](BigDecimal.md) | Tax portion of the charge |  [optional]



