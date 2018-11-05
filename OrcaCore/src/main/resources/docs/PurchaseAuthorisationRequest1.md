
# PurchaseAuthorisationRequest1

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**clientTransactionReference** | **String** | Transaction reference as supplied by client system | 
**purchaseReference** | **String** | The reference number for the original pruchase transaction being authorised. | 
**authDecision** | [**AuthDecisionEnum**](#AuthDecisionEnum) |  | 
**authDecisionReference** | **String** | Any additional auth decision reference details, e.g. a value captured by the consumer when authorising the transaction.   This value is for informational / tracking purposes and is not validated by MobileMart. All responsibilities for validating the value lay with the client systems, e.g. the Client System may send a code via SMS to the Consumer  |  [optional]
**terminalData** | [**TerminalData**](TerminalData.md) |  |  [optional]


<a name="AuthDecisionEnum"></a>
## Enum: AuthDecisionEnum
Name | Value
---- | -----
ACCEPT | &quot;ACCEPT&quot;
DECLINE | &quot;DECLINE&quot;



