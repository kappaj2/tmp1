# PurchasesApi

All URIs are relative to *http://localhost/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**authPurchase**](PurchasesApi.md#authPurchase) | **POST** /purchases/{id}/authorisation | Authorise Purchase
[**createPurchase**](PurchasesApi.md#createPurchase) | **POST** /purchases | Purchase Product
[**getPurchase**](PurchasesApi.md#getPurchase) | **GET** /purchases/{id} | Get Purchase Details
[**reprintReceipt**](PurchasesApi.md#reprintReceipt) | **POST** /purchases/{id}/reprint | Purchase Reprint


<a name="authPurchase"></a>
# **authPurchase**
> PurchaseResponse authPurchase(id, purchaseAuthorisationRequest1)

Authorise Purchase

Obtain explicit authorisation from the customer for the purchase.    Where the purchase has the following requirements:   * requires a multi-step authortisation, or    * where the vendor will take actions that result in a deviation from the purchase face value, for example     * the municipality deducts account arrears from the pre-paid electricity purchase     * ... 

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiClient;
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.invoker.Configuration;
//import com.ktk.mobilemart.core.api.invoker.auth.*;
//import com.ktk.mobilemart.core.api.client.PurchasesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: mobilemart_auth
ApiKeyAuth mobilemart_auth = (ApiKeyAuth) defaultClient.getAuthentication("mobilemart_auth");
mobilemart_auth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//mobilemart_auth.setApiKeyPrefix("Token");

PurchasesApi apiInstance = new PurchasesApi();
Object id = null; // Object | Value returned in field **ID** in response from Purchase Product operation   ( i.e. POST /purchases ) 
PurchaseAuthorisationRequest1 purchaseAuthorisationRequest1 = new PurchaseAuthorisationRequest1(); // PurchaseAuthorisationRequest1 | 
try {
    PurchaseResponse result = apiInstance.authPurchase(id, purchaseAuthorisationRequest1);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PurchasesApi#authPurchase");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**Object**](.md)| Value returned in field **ID** in response from Purchase Product operation   ( i.e. POST /purchases )  |
 **purchaseAuthorisationRequest1** | [**PurchaseAuthorisationRequest1**](PurchaseAuthorisationRequest1.md)|  | [optional]

### Return type

[**PurchaseResponse**](PurchaseResponse.md)

### Authorization

[mobilemart_auth](../README.md#mobilemart_auth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="createPurchase"></a>
# **createPurchase**
> PurchaseResponse createPurchase(purchaseRequest)

Purchase Product

Make a purchase of the selected product.     * If the purchase is fully prosessed      * transactionStatus &#x3D; FULLY_PROCESSED     * transactionDetails is populated                 * If authorisation is required     * transactionStatus &#x3D; AUTH_REQUIRED     * authorisationRequest is populated     * transactionDetails field is not returned 

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiClient;
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.invoker.Configuration;
//import com.ktk.mobilemart.core.api.invoker.auth.*;
//import com.ktk.mobilemart.core.api.client.PurchasesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: mobilemart_auth
ApiKeyAuth mobilemart_auth = (ApiKeyAuth) defaultClient.getAuthentication("mobilemart_auth");
mobilemart_auth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//mobilemart_auth.setApiKeyPrefix("Token");

PurchasesApi apiInstance = new PurchasesApi();
PurchaseRequest purchaseRequest = new PurchaseRequest(); // PurchaseRequest | 
try {
    PurchaseResponse result = apiInstance.createPurchase(purchaseRequest);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PurchasesApi#createPurchase");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **purchaseRequest** | [**PurchaseRequest**](PurchaseRequest.md)|  | [optional]

### Return type

[**PurchaseResponse**](PurchaseResponse.md)

### Authorization

[mobilemart_auth](../README.md#mobilemart_auth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getPurchase"></a>
# **getPurchase**
> PurchaseDetailsResponse getPurchase(id)

Get Purchase Details

Retrieve purchase details. 

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiClient;
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.invoker.Configuration;
//import com.ktk.mobilemart.core.api.invoker.auth.*;
//import com.ktk.mobilemart.core.api.client.PurchasesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: mobilemart_auth
ApiKeyAuth mobilemart_auth = (ApiKeyAuth) defaultClient.getAuthentication("mobilemart_auth");
mobilemart_auth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//mobilemart_auth.setApiKeyPrefix("Token");

PurchasesApi apiInstance = new PurchasesApi();
Object id = null; // Object | Value returned in field **ID** in response from Purchase Product operation   ( i.e. POST /purchases ) 
try {
    PurchaseDetailsResponse result = apiInstance.getPurchase(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PurchasesApi#getPurchase");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**Object**](.md)| Value returned in field **ID** in response from Purchase Product operation   ( i.e. POST /purchases )  |

### Return type

[**PurchaseDetailsResponse**](PurchaseDetailsResponse.md)

### Authorization

[mobilemart_auth](../README.md#mobilemart_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="reprintReceipt"></a>
# **reprintReceipt**
> PurchaseResponse reprintReceipt(id, purchaseReprintRequest)

Purchase Reprint

Retrieve purchase response message for previously purchased product.    This is primarily to be used as an error recovery mechanism in the event of a communication failure  or timeout during a purchase operation. 

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiClient;
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.invoker.Configuration;
//import com.ktk.mobilemart.core.api.invoker.auth.*;
//import com.ktk.mobilemart.core.api.client.PurchasesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: mobilemart_auth
ApiKeyAuth mobilemart_auth = (ApiKeyAuth) defaultClient.getAuthentication("mobilemart_auth");
mobilemart_auth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//mobilemart_auth.setApiKeyPrefix("Token");

PurchasesApi apiInstance = new PurchasesApi();
Object id = null; // Object | Value returned in field **ID** in response from Purchase Product operation   ( i.e. POST /purchases ) 
PurchaseReprintRequest purchaseReprintRequest = new PurchaseReprintRequest(); // PurchaseReprintRequest | 
try {
    PurchaseResponse result = apiInstance.reprintReceipt(id, purchaseReprintRequest);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PurchasesApi#reprintReceipt");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**Object**](.md)| Value returned in field **ID** in response from Purchase Product operation   ( i.e. POST /purchases )  |
 **purchaseReprintRequest** | [**PurchaseReprintRequest**](PurchaseReprintRequest.md)|  | [optional]

### Return type

[**PurchaseResponse**](PurchaseResponse.md)

### Authorization

[mobilemart_auth](../README.md#mobilemart_auth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

