package com.ktk.orca.core.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktk.orca.core.IntegrationTest;
import com.ktk.orca.core.api.model.CatalogCategory;
import com.ktk.orca.core.api.model.ProductCategoryResponse;
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

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;

/**
 * API tests for CategoriesApi
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
@Category(IntegrationTest.class)
@Ignore
public class CategoriesApiTest {

    @Autowired
    private CategoriesApi categoriesApi;

    @Autowired
    @Qualifier("jsonObjectMapper")
    private ObjectMapper objectMapper;

    @Before
    public void setup() {

    }

    /**
     * Get Product Categories
     * <p>
     * Get Product Category list.   If includeProducts&#x3D;true, then includeVendors is implied and does not need to be specified
     *
     * @throws RestClientException if the Api call fails
     */
    @Test
    public void getProductCategoryListTest() {
        Boolean includeVendors = true;
        Boolean includeProducts = true;

        try {
            ProductCategoryResponse response = categoriesApi.getProductCategoryList(includeVendors, includeProducts);

            List<CatalogCategory> categoriesList = response.getCategories();
            assertTrue(categoriesList.size() >= 1);
        } catch (RestClientException apie) {
            fail("Error occurred");
        }
    }

}
