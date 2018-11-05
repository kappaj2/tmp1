
# TransactionReportStatusResponse

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**transactionRef** | **String** | MobileMart transaction reference number. Uniquely allocated per client initiated request. |  [optional]
**reportURL** | **String** | URL where report / listing is available  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]


<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
AWAIT_PROCESSING | &quot;AWAIT_PROCESSING&quot;
PROCESSING | &quot;PROCESSING&quot;
AVAILABLE | &quot;AVAILABLE&quot;



