
# AccountFundsTransaction1

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**transactionType** | [**TransactionTypeEnum**](#TransactionTypeEnum) |  | 
**transactionReference** | **String** | Reference number provided by the client to be included in the actual funding transaction (e.g. EFT) to allow matching  The client specified transaction reference is used by MobileMart to identify the DEPOSIT, or entered when loading a EFT for a withdrawal request  | 
**date** | [**LocalDate**](LocalDate.md) | Date notation as defined by RFC 3339, section 5.6, for example, 2017-07-21  |  [optional]
**amount** | [**BigDecimal**](BigDecimal.md) | Amount of the funding transaction.   The amount may be subject to minimum and maximum amounts as per the Client Commercial Agreement  | 
**currency** | **String** | ISO 3 character currency code. Default - ZAR (South African Rand) |  [optional]
**instructions** | **String** | Any special instruction or notes pertinent to this funds transaction request |  [optional]


<a name="TransactionTypeEnum"></a>
## Enum: TransactionTypeEnum
Name | Value
---- | -----
DEPOSIT | &quot;DEPOSIT&quot;
WITHDRAW | &quot;WITHDRAW&quot;



