# VendorsApi

All URIs are relative to *http://localhost/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getVendorList**](VendorsApi.md#getVendorList) | **GET** /vendors | Get Vendors


<a name="getVendorList"></a>
# **getVendorList**
> ProductVendorsResponse getVendorList(includeProducts)

Get Vendors

Get Vendor list. Paged result list

### Example
```java
// Import classes:
//import com.ktk.mobilemart.core.api.invoker.ApiException;
//import com.ktk.mobilemart.core.api.client.VendorsApi;


VendorsApi apiInstance = new VendorsApi();
Boolean includeProducts = true; // Boolean | 
try {
    ProductVendorsResponse result = apiInstance.getVendorList(includeProducts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling VendorsApi#getVendorList");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **includeProducts** | **Boolean**|  | [optional]

### Return type

[**ProductVendorsResponse**](ProductVendorsResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

