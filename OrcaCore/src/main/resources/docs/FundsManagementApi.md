# FundsManagementApi

All URIs are relative to *http://localhost/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAccountFunds**](FundsManagementApi.md#getAccountFunds) | **GET** /accounts/{id}/funds | Account Funds Summary
[**notifyAccountFundsTransaction**](FundsManagementApi.md#notifyAccountFundsTransaction) | **POST** /accounts/{id}/funds | Funds Transaction Notice


<a name="getAccountFunds"></a>
# **getAccountFunds**
> AccountFundsResponse getAccountFunds(id)

Account Funds Summary

Get funds balances for account

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiClient;
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.invoker.Configuration;
//import com.ktk.mobilemart.core.api.invoker.auth.*;
//import com.ktk.mobilemart.core.api.client.FundsManagementApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: mobilemart_auth
ApiKeyAuth mobilemart_auth = (ApiKeyAuth) defaultClient.getAuthentication("mobilemart_auth");
mobilemart_auth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//mobilemart_auth.setApiKeyPrefix("Token");

FundsManagementApi apiInstance = new FundsManagementApi();
Object id = null; // Object | Account identifier
try {
    AccountFundsResponse result = apiInstance.getAccountFunds(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsManagementApi#getAccountFunds");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**Object**](.md)| Account identifier |

### Return type

[**AccountFundsResponse**](AccountFundsResponse.md)

### Authorization

[mobilemart_auth](../README.md#mobilemart_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="notifyAccountFundsTransaction"></a>
# **notifyAccountFundsTransaction**
> AccountFundsTransactionResponse notifyAccountFundsTransaction(id, accountFundsTransaction1)

Funds Transaction Notice

Create funds deposit or withdrawal notification

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiClient;
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.invoker.Configuration;
//import com.ktk.mobilemart.core.api.invoker.auth.*;
//import com.ktk.mobilemart.core.api.client.FundsManagementApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: mobilemart_auth
ApiKeyAuth mobilemart_auth = (ApiKeyAuth) defaultClient.getAuthentication("mobilemart_auth");
mobilemart_auth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//mobilemart_auth.setApiKeyPrefix("Token");

FundsManagementApi apiInstance = new FundsManagementApi();
Object id = null; // Object | Account identifier
AccountFundsTransaction1 accountFundsTransaction1 = new AccountFundsTransaction1(); // AccountFundsTransaction1 | 
try {
    AccountFundsTransactionResponse result = apiInstance.notifyAccountFundsTransaction(id, accountFundsTransaction1);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling FundsManagementApi#notifyAccountFundsTransaction");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**Object**](.md)| Account identifier |
 **accountFundsTransaction1** | [**AccountFundsTransaction1**](AccountFundsTransaction1.md)|  | [optional]

### Return type

[**AccountFundsTransactionResponse**](AccountFundsTransactionResponse.md)

### Authorization

[mobilemart_auth](../README.md#mobilemart_auth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

