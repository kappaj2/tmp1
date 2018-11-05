package com.ktk.orca.core.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktk.orca.core.IntegrationTest;
import com.ktk.orca.core.api.model.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * API tests for ProductsApi
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
@Category(IntegrationTest.class)
@Ignore
public class ProductsApiTest {

    @Autowired
    private ProductsApi productsApi;

    @Autowired
    @Qualifier("jsonObjectMapper")
    private ObjectMapper objectMapper;

    @Before
    public void setup() {

    }

    /**
     * Get Product Catalog
     * <p>
     * Get a structured Product list forming a catalog grouped by   * Product Category   * Vendor
     *
     * @throws RestClientException if the Api call fails
     */
    @Test
    public void getProductCatalogTest() throws Exception {

        String rawData = "";
        Long startTime = System.currentTimeMillis();
        Long duration = 0L;
        int responseCode = -1;
        List<String> categories = new ArrayList<>();
        List<String> vendors = new ArrayList<>();

        ProductCatalogResponse response = null;

        try {
            response = productsApi.getProductCatalog(categories, vendors);

        } catch (RestClientException apie) {
            fail("Error occurred");

        } finally {
            //log.info("Request responseData is : " + rawData + " and responseCode : " + responseCode + " in time of : " + duration);
        }

        if (response != null && response.getCatalog() != null) {
            ProductCatalogResponseCatalog catalog = response.getCatalog();

            String catal = objectMapper.writeValueAsString(catalog);

            ProductCatalogResponseCatalog pCat = objectMapper.readValue(catal, ProductCatalogResponseCatalog.class);

            List<CatalogCategory> categoriesList = catalog.getCategories();
            categoriesList.stream().forEach(cat -> {
                assertNotNull("Category name should not be null", cat.getName());
                assertTrue("There should bo more than one vendor. Actual size : " + cat.getVendors().size(), cat.getVendors().size() >= 2);
            });
        } else {
            fail("Error retrieving product catalog");
        }
    }

    /**
     * Get Products
     * <p>
     * Get Product list meeting query criteria. Paged result list
     *
     * @throws RestClientException if the Api call fails
     */
    @Test
    public void getProductListTest() {

        //  TODO - test the paging functionality.

        List<String> categories = new ArrayList<>();
        List<String> vendors = new ArrayList<>();
        Integer pageStart = 1;
        Integer pageSize = 15;

        try {
            ProductListResponse response = productsApi.getProductList(categories, vendors, pageStart, pageSize);
            String txRef = response.getTransactionRef();
            List<Product> productList = response.getProducts();
            assertTrue(productList.size() > 0);
        } catch (RestClientException apie) {
            fail("Error occurred");
        }
    }

}
