package com.ktk.orca.core.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktk.orca.core.api.client.VendorsApi;
import com.ktk.orca.core.api.invoker.ApiClient;
import com.ktk.orca.core.api.model.ProductVendorsResponse;
import com.ktk.orca.core.config.ApplicationProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VendorServiceImplTest {

    private Fixture fixture;
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        fixture = new Fixture();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testTransactionRefIs() throws Exception {
        String transactionRef = "12222112";
        fixture.givenTransactionRef(transactionRef);
        fixture.givenIncludeProducsts(true);
        fixture.whenGetVendorList();
        fixture.thenTransactionRefShouldBe(transactionRef);
    }

    private class Fixture {

        //  Set to default success - will test negative with explicit setting.
        private HttpStatus httpStatus = HttpStatus.OK;
        private boolean includeProducts;
        private String transactionRef;

        private ProductVendorsResponse productVendorsResponse;
        private ProductVendorsResponse vendorResponse;

        @Mock
        private ApiClient apiClient;
        @Mock
        private BaseServiceUtil baseServiceUtil;
        @Mock
        private ApplicationProperties props;
        @Mock
        private MessageSource messageSource;
        @Mock
        private VendorsApi vendorsApi;

        @InjectMocks
        private VendorServiceImpl vendorService;

        public Fixture() {
            MockitoAnnotations.initMocks(this);
        }

        public void givenIncludeProducsts(Boolean includePrpducts) {
            this.includeProducts = includeProducts;
        }

        public void givenTransactionRef(String transactionRef) {
            this.transactionRef = transactionRef;
        }

        public void whenGetVendorList() throws Exception {

            productVendorsResponse = new ProductVendorsResponse();
            productVendorsResponse.setTransactionRef(transactionRef);

            when(vendorsApi.getVendorList(includeProducts))
                    .thenReturn(productVendorsResponse);

            when(baseServiceUtil.createNewObjectNode())
                    .thenReturn(objectMapper.createObjectNode());

            when(vendorsApi.getApiClient())
                    .thenReturn(apiClient);

            when(apiClient.getStatusCode())
                    .thenReturn(httpStatus);

            vendorResponse = vendorService.getVendorList(includeProducts);

        }

        public void thenTransactionRefShouldBe(String transactionRef) {
            assertEquals(transactionRef, vendorResponse.getTransactionRef());
        }
    }
}