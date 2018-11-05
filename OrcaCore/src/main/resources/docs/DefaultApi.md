# DefaultApi

All URIs are relative to *http://localhost/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**accountsIdFundsOptions**](DefaultApi.md#accountsIdFundsOptions) | **OPTIONS** /accounts/{id}/funds | Default CORS support
[**accountsIdOptions**](DefaultApi.md#accountsIdOptions) | **OPTIONS** /accounts/{id} | Default CORS support
[**productCatalogOptions**](DefaultApi.md#productCatalogOptions) | **OPTIONS** /product_catalog | Default CORS support
[**productCategoriesOptions**](DefaultApi.md#productCategoriesOptions) | **OPTIONS** /product_categories | Default CORS support
[**productsOptions**](DefaultApi.md#productsOptions) | **OPTIONS** /products | Default CORS support
[**purchasesIdAuthorisationOptions**](DefaultApi.md#purchasesIdAuthorisationOptions) | **OPTIONS** /purchases/{id}/authorisation | Default CORS support
[**purchasesIdOptions**](DefaultApi.md#purchasesIdOptions) | **OPTIONS** /purchases/{id} | Default CORS support
[**purchasesIdReprintOptions**](DefaultApi.md#purchasesIdReprintOptions) | **OPTIONS** /purchases/{id}/reprint | Default CORS support
[**purchasesOptions**](DefaultApi.md#purchasesOptions) | **OPTIONS** /purchases | Default CORS support
[**transactionReportsIdOptions**](DefaultApi.md#transactionReportsIdOptions) | **OPTIONS** /transaction_reports/{id} | Default CORS support
[**transactionsOptions**](DefaultApi.md#transactionsOptions) | **OPTIONS** /transactions | Default CORS support
[**vendorsOptions**](DefaultApi.md#vendorsOptions) | **OPTIONS** /vendors | Default CORS support


<a name="accountsIdFundsOptions"></a>
# **accountsIdFundsOptions**
> accountsIdFundsOptions(id)

Default CORS support

Enable CORS by returning correct headers

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.client.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Object id = null; // Object | Account identifier
try {
    apiInstance.accountsIdFundsOptions(id);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#accountsIdFundsOptions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**Object**](.md)| Account identifier |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="accountsIdOptions"></a>
# **accountsIdOptions**
> accountsIdOptions(id)

Default CORS support

Enable CORS by returning correct headers

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.client.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Object id = null; // Object | Account identifier
try {
    apiInstance.accountsIdOptions(id);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#accountsIdOptions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**Object**](.md)| Account identifier |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="productCatalogOptions"></a>
# **productCatalogOptions**
> productCatalogOptions()

Default CORS support

Enable CORS by returning correct headers

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.client.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    apiInstance.productCatalogOptions();
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#productCatalogOptions");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="productCategoriesOptions"></a>
# **productCategoriesOptions**
> productCategoriesOptions()

Default CORS support

Enable CORS by returning correct headers

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.client.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    apiInstance.productCategoriesOptions();
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#productCategoriesOptions");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="productsOptions"></a>
# **productsOptions**
> productsOptions()

Default CORS support

Enable CORS by returning correct headers

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.client.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    apiInstance.productsOptions();
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#productsOptions");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="purchasesIdAuthorisationOptions"></a>
# **purchasesIdAuthorisationOptions**
> purchasesIdAuthorisationOptions(id)

Default CORS support

Enable CORS by returning correct headers

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.client.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Object id = null; // Object | Value returned in field **ID** in response from Purchase Product operation   ( i.e. POST /purchases ) 
try {
    apiInstance.purchasesIdAuthorisationOptions(id);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#purchasesIdAuthorisationOptions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**Object**](.md)| Value returned in field **ID** in response from Purchase Product operation   ( i.e. POST /purchases )  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="purchasesIdOptions"></a>
# **purchasesIdOptions**
> purchasesIdOptions(id)

Default CORS support

Enable CORS by returning correct headers

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.client.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Object id = null; // Object | Value returned in field **ID** in response from Purchase Product operation   ( i.e. POST /purchases ) 
try {
    apiInstance.purchasesIdOptions(id);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#purchasesIdOptions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**Object**](.md)| Value returned in field **ID** in response from Purchase Product operation   ( i.e. POST /purchases )  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="purchasesIdReprintOptions"></a>
# **purchasesIdReprintOptions**
> purchasesIdReprintOptions(id)

Default CORS support

Enable CORS by returning correct headers

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiException;
DefaultApi


DefaultApi apiInstance = new DefaultApi();
Object id = null; // Object | Value returned in field **ID** in response from Purchase Product operation   ( i.e. POST /purchases ) 
try {
    apiInstance.purchasesIdReprintOptions(id);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#purchasesIdReprintOptions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**Object**](.md)| Value returned in field **ID** in response from Purchase Product operation   ( i.e. POST /purchases )  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="purchasesOptions"></a>
# **purchasesOptions**
> purchasesOptions()

Default CORS support

Enable CORS by returning correct headers

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.client.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    apiInstance.purchasesOptions();
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#purchasesOptions");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="transactionReportsIdOptions"></a>
# **transactionReportsIdOptions**
> transactionReportsIdOptions(id)

Default CORS support

Enable CORS by returning correct headers

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.client.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Object id = null; // Object | Value returned in field **transactionListId** in response from GET /transactions
try {
    apiInstance.transactionReportsIdOptions(id);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#transactionReportsIdOptions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**Object**](.md)| Value returned in field **transactionListId** in response from GET /transactions |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="transactionsOptions"></a>
# **transactionsOptions**
> transactionsOptions()

Default CORS support

Enable CORS by returning correct headers

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.client.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    apiInstance.transactionsOptions();
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#transactionsOptions");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="vendorsOptions"></a>
# **vendorsOptions**
> vendorsOptions()

Default CORS support

Enable CORS by returning correct headers

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.client.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    apiInstance.vendorsOptions();
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#vendorsOptions");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

