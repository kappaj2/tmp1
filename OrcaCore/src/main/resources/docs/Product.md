
# Product

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**requiresAuthorisation** | [**RequiresAuthorisationEnum**](#RequiresAuthorisationEnum) | Authorisation requirements for the product.   If ALWAYS or CONDITIONAL and conditions are met, an authorisation request response is returned in the PurchaseResponse. Details of the AuthorisationRequest must be presented to the consumer to allow either Acceptance ot Decline of the transaction.   As authorisation requirements can change, client systems should expect an authorisationflow and  not rely on existing NONE, ALWAYS or CONDITIONAL to be fixed for the lifetime of a product.  |  [optional]
**expiryDate** | **String** | Date-Time notation as defined by RFC 3339, section 5.6, for example, 2017-07-21T17:32:28Z / 2017-07-21T19:32:28+02:00  Values in requests are accepted in any timezone and will be converted to UTC before being processed / stored by MobileMart.  All date-time values in responses are UTC timezone.  |  [optional]
**vendor** | **String** | Product vendor |  [optional]
**name** | **String** | Product name |  [optional]
**activeDate** | **String** | Date-Time notation as defined by RFC 3339, section 5.6, for example, 2017-07-21T17:32:28Z / 2017-07-21T19:32:28+02:00  Values in requests are accepted in any timezone and will be converted to UTC before being processed / stored by MobileMart.  All date-time values in responses are UTC timezone.  |  [optional]
**productStatus** | **String** | Product status |  [optional]
**currency** | **String** | ISO 3 character currency code. Default - ZAR (South African Rand) |  [optional]
**id** | **String** | Product id |  [optional]
**category** | **String** | Product category |  [optional]
**identifierFields** | [**List&lt;IdentifierField&gt;**](IdentifierField.md) | Product identifier fields.   If the product can be used for a non-specific device (e.g. airtime purchase), no identifier fields are returned.  Where the product is targeted at a specific device (e.g. cellphone or electricity meter),  the list of identifier fields to be provided.  |  [optional]
**productType** | **String** | Product type |  [optional]


<a name="RequiresAuthorisationEnum"></a>
## Enum: RequiresAuthorisationEnum
Name | Value
---- | -----
NONE | &quot;NONE&quot;
ALWAYS | &quot;ALWAYS&quot;
CONDITIONAL | &quot;CONDITIONAL&quot;



