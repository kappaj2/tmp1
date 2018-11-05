# TransactionListApi

All URIs are relative to *http://localhost/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getTransactionReportStatus**](TransactionListApi.md#getTransactionReportStatus) | **GET** /transaction_reports/{id} | Transaction Report Status
[**getTransactions**](TransactionListApi.md#getTransactions) | **GET** /transactions | Transaction List


<a name="getTransactionReportStatus"></a>
# **getTransactionReportStatus**
> TransactionReportStatusResponse getTransactionReportStatus(id)

Transaction Report Status

Check status of transaction report / listing previously requested

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiClient;
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.invoker.Configuration;
//import com.ktk.mobilemart.core.api.invoker.auth.*;
//import com.ktk.mobilemart.core.api.client.TransactionListApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: mobilemart_auth
ApiKeyAuth mobilemart_auth = (ApiKeyAuth) defaultClient.getAuthentication("mobilemart_auth");
mobilemart_auth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//mobilemart_auth.setApiKeyPrefix("Token");

TransactionListApi apiInstance = new TransactionListApi();
Object id = null; // Object | Value returned in field **transactionListId** in response from GET /transactions
try {
    TransactionReportStatusResponse result = apiInstance.getTransactionReportStatus(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TransactionListApi#getTransactionReportStatus");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**Object**](.md)| Value returned in field **transactionListId** in response from GET /transactions |

### Return type

[**TransactionReportStatusResponse**](TransactionReportStatusResponse.md)

### Authorization

[mobilemart_auth](../README.md#mobilemart_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTransactions"></a>
# **getTransactions**
> TransactionListProcessingResponse getTransactions(periodStart, periodEnd, type)

Transaction List

Transactions for the period. Default period - month to date

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiClient;
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.invoker.Configuration;
//import com.ktk.mobilemart.core.api.invoker.auth.*;
//import com.ktk.mobilemart.core.api.client.TransactionListApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: mobilemart_auth
ApiKeyAuth mobilemart_auth = (ApiKeyAuth) defaultClient.getAuthentication("mobilemart_auth");
mobilemart_auth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//mobilemart_auth.setApiKeyPrefix("Token");

TransactionListApi apiInstance = new TransactionListApi();
LocalDate periodStart = new LocalDate(); // LocalDate | Default - 1st of current month
LocalDate periodEnd = new LocalDate(); // LocalDate | Default - today
String type = "type_example"; // String | Default - PURCHASES   To specify multiple transaction types, values must be comma separated.  e.g.  GET http://..../transactions?type=PURCHASES,COMMISSION 
try {
    TransactionListProcessingResponse result = apiInstance.getTransactions(periodStart, periodEnd, type);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TransactionListApi#getTransactions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **periodStart** | **LocalDate**| Default - 1st of current month | [optional]
 **periodEnd** | **LocalDate**| Default - today | [optional]
 **type** | **String**| Default - PURCHASES   To specify multiple transaction types, values must be comma separated.  e.g.  GET http://..../transactions?type&#x3D;PURCHASES,COMMISSION  | [optional] [enum: ALL, COMMISSION, CORRECTION, FEES, PURCHASES, DEPOSIT, WITHDRAW]

### Return type

[**TransactionListProcessingResponse**](TransactionListProcessingResponse.md)

### Authorization

[mobilemart_auth](../README.md#mobilemart_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

