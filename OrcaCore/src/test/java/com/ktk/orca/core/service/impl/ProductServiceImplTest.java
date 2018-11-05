package com.ktk.orca.core.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktk.orca.core.api.client.ProductsApi;
import com.ktk.orca.core.api.invoker.ApiClient;
import com.ktk.orca.core.api.model.ProductCatalogResponse;
import com.ktk.orca.core.api.model.ProductListResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    private Fixture fixture;
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        fixture = new Fixture();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetProductCatalog() throws Exception {

        String transactionRef = "1234567";

        fixture.givenCatagoriesListIs(new ArrayList<>());
        fixture.givenVendorsListIs(new ArrayList<>());
        fixture.givenTransactionRefIs(transactionRef);
        fixture.whenGetProductCatalog();
        fixture.thenProductCatalogTransactionRefShouldBe(transactionRef);
    }

    @Test
    public void testGetProductList() throws Exception {

        String transactionRef = "12345678";
        fixture.givenCatagoriesListIs(new ArrayList<>());
        fixture.givenVendorsListIs(new ArrayList<>());
        fixture.givenTransactionRefIs(transactionRef);
        fixture.whenGetProductList();
        fixture.thenProductListTransactionRefShouldBe(transactionRef);
    }

    private class Fixture {

        private ProductCatalogResponse productCatalogResponse;
        private ProductCatalogResponse catalogResponse;
        private ProductListResponse productListResponse;
        private ProductListResponse listResponse;

        private List<String> categories;
        private List<String> vendors;
        private Integer pageSize = Integer.parseInt("24");
        private Integer pageStart = Integer.parseInt("1");
        private String transactionRef;
        //  Set to default success - will test negative with explicit setting.
        private HttpStatus httpStatus = HttpStatus.OK;

        @Mock
        private ApiClient apiClient;
        @Mock
        private BaseServiceUtil baseServiceUtil;
        @Mock
        private ProductsApi productsApi;
        @Mock
        private MessageSource messageSource;


        @InjectMocks
        private ProductServiceImpl productService;

        public Fixture() {
            MockitoAnnotations.initMocks(this);
        }

        public void givenCatagoriesListIs(List<String> categories) {
            this.categories = categories;
        }

        public void givenVendorsListIs(List<String> vendors) {
            this.vendors = vendors;
        }

        public void givenTransactionRefIs(String transactionRef) {
            this.transactionRef = transactionRef;
        }

        public void whenGetProductCatalog() throws Exception {
            productCatalogResponse = new ProductCatalogResponse();
            productCatalogResponse.setTransactionRef(transactionRef);

            when(productsApi.getProductCatalog(categories, vendors))
                    .thenReturn(productCatalogResponse);

            when(baseServiceUtil.createNewObjectNode())
                    .thenReturn(objectMapper.createObjectNode());

            when(productsApi.getApiClient())
                    .thenReturn(apiClient);

            when(apiClient.getStatusCode())
                    .thenReturn(httpStatus);

            catalogResponse = productService.getProductCatalog(categories, vendors);
        }

        public void whenGetProductList() throws Exception {

            productListResponse = new ProductListResponse();
            productListResponse.setTransactionRef(transactionRef);

            when(productsApi.getProductList(categories, vendors, pageStart, pageSize))
                    .thenReturn(productListResponse);

            when(baseServiceUtil.createNewObjectNode())
                    .thenReturn(objectMapper.createObjectNode());

            when(productsApi.getApiClient())
                    .thenReturn(apiClient);

            when(apiClient.getStatusCode())
                    .thenReturn(httpStatus);

            listResponse = productService.getProductList(categories, vendors, pageStart, pageSize);
        }

        public void thenProductCatalogTransactionRefShouldBe(String transactionRef) {
            assertEquals(transactionRef, catalogResponse.getTransactionRef());
        }

        public void thenProductListTransactionRefShouldBe(String transactionRef) {
            assertEquals(transactionRef, listResponse.getTransactionRef());
        }
    }
}