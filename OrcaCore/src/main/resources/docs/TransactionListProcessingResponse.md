
# TransactionListProcessingResponse

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**transactionListId** | **String** | Unique identifier used to query status of list processing |  [optional]
**transactionRef** | **String** | MobileMart transaction reference number. Uniquely allocated per client initiated request. |  [optional]
**listURL** | **String** | URL where listing will be available once processing is complete   It is a client responsibility to periodically poll the URL or to query Transaction List status using the **transactionListId** returned with  this response  |  [optional]



