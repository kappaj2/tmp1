# AccountApi

All URIs are relative to *http://localhost/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAccount**](AccountApi.md#getAccount) | **GET** /accounts/{id} | Account Details


<a name="getAccount"></a>
# **getAccount**
> AccountDetailsResponse getAccount(id, periodStart, periodEnd, purchasesSummary, vendorSummary)

Account Details

Return account details. Default - returns account summary with purchases totals by product category for the calendar month to date .   Additional details are requested via the following query parameters   * **purchases** - include last 30 product purchase transactions   * **vendorBreakdown** - include per vendor breakdown in product category totals   * **periodStart, periodEnd** - Select a custom period to aggregate purchases by specifying start and end dates    

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiClient;
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.invoker.Configuration;
//import com.ktk.mobilemart.core.api.invoker.auth.*;
//import com.ktk.mobilemart.core.api.client.AccountApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: mobilemart_auth
ApiKeyAuth mobilemart_auth = (ApiKeyAuth) defaultClient.getAuthentication("mobilemart_auth");
mobilemart_auth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//mobilemart_auth.setApiKeyPrefix("Token");

AccountApi apiInstance = new AccountApi();
Object id = null; // Object | Account identifier
LocalDate periodStart = new LocalDate(); // LocalDate | Default - 1st of current month
LocalDate periodEnd = new LocalDate(); // LocalDate | Default - today
Boolean purchasesSummary = true; // Boolean | 
Boolean vendorSummary = true; // Boolean | 
try {
    AccountDetailsResponse result = apiInstance.getAccount(id, periodStart, periodEnd, purchasesSummary, vendorSummary);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#getAccount");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**Object**](.md)| Account identifier |
 **periodStart** | **LocalDate**| Default - 1st of current month | [optional]
 **periodEnd** | **LocalDate**| Default - today | [optional]
 **purchasesSummary** | **Boolean**|  | [optional]
 **vendorSummary** | **Boolean**|  | [optional]

### Return type

[**AccountDetailsResponse**](AccountDetailsResponse.md)

### Authorization

[mobilemart_auth](../README.md#mobilemart_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

