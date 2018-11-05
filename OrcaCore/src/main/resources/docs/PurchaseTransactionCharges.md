
# PurchaseTransactionCharges

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**amount** | **Integer** | Charge in cents (total, including any taxes) |  [optional]
**accountDescription** | **String** | Account description the charge is applied to |  [optional]
**chargeType** | [**ChargeTypeEnum**](#ChargeTypeEnum) |  |  [optional]
**description** | **String** | Charge description |  [optional]
**currency** | **String** | ISO 3 character currency code. Default - ZAR (South African Rand) |  [optional]
**remainingBalance** | **Integer** | Where the charge is against a debt balance, the remaining amount after this charge has been applied |  [optional]
**taxAmount** | [**BigDecimal**](BigDecimal.md) | Tax portion of the charge |  [optional]
**accountNumber** | **String** | Account number the charge is applied to |  [optional]


<a name="ChargeTypeEnum"></a>
## Enum: ChargeTypeEnum
Name | Value
---- | -----
DEBT_REPAY | &quot;DEBT_REPAY&quot;
SERVICE_FEE | &quot;SERVICE_FEE&quot;
VENDOR_FEE | &quot;VENDOR_FEE&quot;



