# CategoriesApi

All URIs are relative to *http://localhost/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getProductCategoryList**](CategoriesApi.md#getProductCategoryList) | **GET** /product_categories | Get Product Categories


<a name="getProductCategoryList"></a>
# **getProductCategoryList**
> ProductCategoryResponse getProductCategoryList(includeVendors, includeProducts)

Get Product Categories

Get Product Category list.   If includeProducts&#x3D;true, then includeVendors is implied and does not need to be specified 

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.client.CategoriesApi;


CategoriesApi apiInstance = new CategoriesApi();
Boolean includeVendors = true; // Boolean | 
Boolean includeProducts = true; // Boolean | 
try {
    ProductCategoryResponse result = apiInstance.getProductCategoryList(includeVendors, includeProducts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CategoriesApi#getProductCategoryList");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **includeVendors** | **Boolean**|  | [optional]
 **includeProducts** | **Boolean**|  | [optional]

### Return type

[**ProductCategoryResponse**](ProductCategoryResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

