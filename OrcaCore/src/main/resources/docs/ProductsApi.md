# ProductsApi

All URIs are relative to *http://localhost/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getProductCatalog**](ProductsApi.md#getProductCatalog) | **GET** /product_catalog | Get Product Catalog
[**getProductList**](ProductsApi.md#getProductList) | **GET** /products | Get Products


<a name="getProductCatalog"></a>
# **getProductCatalog**
> ProductCatalogResponse getProductCatalog()

Get Product Catalog

Get a structured Product list forming a catalog grouped by   * Product Category   * Vendor 

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiClient;
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.invoker.Configuration;
//import com.ktk.mobilemart.core.api.invoker.auth.*;
//import com.ktk.mobilemart.core.api.client.ProductsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: mobilemart_auth
ApiKeyAuth mobilemart_auth = (ApiKeyAuth) defaultClient.getAuthentication("mobilemart_auth");
mobilemart_auth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//mobilemart_auth.setApiKeyPrefix("Token");

ProductsApi apiInstance = new ProductsApi();
try {
    ProductCatalogResponse result = apiInstance.getProductCatalog();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProductsApi#getProductCatalog");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ProductCatalogResponse**](ProductCatalogResponse.md)

### Authorization

[mobilemart_auth](../README.md#mobilemart_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getProductList"></a>
# **getProductList**
> ProductCatalogFlatResponse getProductList(categories, vendor, pageStart, pageSize)

Get Products

Get Product list meeting query criteria. Paged result list

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiClient;
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.invoker.Configuration;
//import com.ktk.mobilemart.core.api.invoker.auth.*;
//import com.ktk.mobilemart.core.api.client.ProductsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: mobilemart_auth
ApiKeyAuth mobilemart_auth = (ApiKeyAuth) defaultClient.getAuthentication("mobilemart_auth");
mobilemart_auth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//mobilemart_auth.setApiKeyPrefix("Token");

ProductsApi apiInstance = new ProductsApi();
Object categories = null; // Object | 
Object vendor = null; // Object | 
Integer pageStart = 56; // Integer | 
Integer pageSize = 56; // Integer | 
try {
    ProductCatalogFlatResponse result = apiInstance.getProductList(categories, vendor, pageStart, pageSize);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProductsApi#getProductList");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **categories** | [**Object**](.md)|  | [optional]
 **vendor** | [**Object**](.md)|  | [optional]
 **pageStart** | **Integer**|  | [optional]
 **pageSize** | **Integer**|  | [optional]

### Return type

[**ProductCatalogFlatResponse**](ProductCatalogFlatResponse.md)

### Authorization

[mobilemart_auth](../README.md#mobilemart_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

