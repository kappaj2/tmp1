package com.ktk.orca.core.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktk.orca.core.IntegrationTest;
import com.ktk.orca.core.api.model.CatalogVendor;
import com.ktk.orca.core.api.model.ProductVendorsResponse;
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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * API tests for VendorsApi
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
@Category(IntegrationTest.class)
@Ignore
public class VendorsApiTest {

    @Autowired
    private VendorsApi vendorsApi;

    @Autowired
    @Qualifier("jsonObjectMapper")
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
    }

    /**
     * Get Vendors
     * <p>
     * Get Vendor list. Paged result list
     *
     * @throws RestClientException if the Api call fails
     */
    @Test
    public void getVendorListTest() {
        Boolean includeProducts = true;
        try {
            ProductVendorsResponse response = vendorsApi.getVendorList(includeProducts);
            String responseTransactionRef = response.getTransactionRef();

            List<CatalogVendor> catalogVendorList = response.getVendors();

            assertEquals("KJG.876.MMNV", responseTransactionRef);
            assertTrue(catalogVendorList.size() >= 1);
        } catch (RestClientException apie) {
            fail("Error occurred");
        }
    }

}
