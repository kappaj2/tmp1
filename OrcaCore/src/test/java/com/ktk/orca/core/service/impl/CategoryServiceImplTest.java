package com.ktk.orca.core.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktk.orca.core.api.client.CategoriesApi;
import com.ktk.orca.core.api.invoker.ApiClient;
import com.ktk.orca.core.api.model.CatalogCategory;
import com.ktk.orca.core.api.model.ProductCategoryResponse;
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
public class CategoryServiceImplTest {

    private Fixture fixture;
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        fixture = new Fixture();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetProductCategoryList() throws Exception {

        String transactionRef = "transref123";
        List<CatalogCategory> catalogCategoryList = new ArrayList<>();

        fixture.givenIncludeProductsIs(true);
        fixture.givenIncludeVendorsIs(true);
        fixture.giveTransactionRefIs(transactionRef);
        fixture.givenCatalogCatagoryListIs(catalogCategoryList);
        fixture.whenGetProductCategoryList();
        fixture.thenTransactionRefShouldBe(transactionRef);
        fixture.thenCatalogCatagoryListSizeShouldBe(0);
    }

    private class Fixture {

        private ProductCategoryResponse productCategoryResponse;
        private ProductCategoryResponse response;

        private Boolean includeVendors;
        private Boolean includeProducts;
        private String transactionRef;
        private List<CatalogCategory> catalogCategoryList;
        //  Set to default success - will test negative with explicit setting.
        private HttpStatus httpStatus = HttpStatus.OK;

        @Mock
        private ApiClient apiClient;
        @Mock
        private BaseServiceUtil baseServiceUtil;
        @Mock
        private CategoriesApi categoriesApi;
        @Mock
        private MessageSource messageSource;

        @InjectMocks
        private CategoryServiceImpl categoryService;

        public Fixture() {
            MockitoAnnotations.initMocks(this);
        }

        public void givenIncludeVendorsIs(Boolean includeVendors) {
            this.includeVendors = includeVendors;
        }

        public void givenIncludeProductsIs(Boolean includeProducts) {
            this.includeProducts = includeProducts;
        }

        public void giveTransactionRefIs(String transactionRef) {
            this.transactionRef = transactionRef;
        }

        public void givenCatalogCatagoryListIs(List<CatalogCategory> catalogCategoryList) {
            this.catalogCategoryList = catalogCategoryList;
        }

        public void whenGetProductCategoryList() throws Exception{
            productCategoryResponse = new ProductCategoryResponse();
            productCategoryResponse.setTransactionRef(transactionRef);
            productCategoryResponse.setCategories(catalogCategoryList);

            when(categoriesApi.getProductCategoryList(includeVendors, includeProducts))
                    .thenReturn(productCategoryResponse);

            when(baseServiceUtil.createNewObjectNode())
                    .thenReturn(objectMapper.createObjectNode());

            when(categoriesApi.getApiClient())
                    .thenReturn(apiClient);

            when(apiClient.getStatusCode())
                    .thenReturn(httpStatus);

            response = categoryService.getProductCategoryList(includeVendors, includeProducts);
        }

        public void thenTransactionRefShouldBe(String transRef) {
            assertEquals(transRef, response.getTransactionRef());
        }

        public void thenCatalogCatagoryListSizeShouldBe(int siz) {
            assertEquals(siz, response.getCategories().size());
        }
    }

}