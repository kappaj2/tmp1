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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * API tests for PurchasesApi
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
@Category(IntegrationTest.class)
@Ignore
public class PurchasesApiTest {

    @Autowired
    private PurchasesApi purchasesApi;

    @Autowired
    @Qualifier("jsonObjectMapper")
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
    }

    /**
     * Authorise Purchase
     * <p>
     * Obtain explicit authorisation from the customer for the purchase.    Where the purchase has the following requirements:   * requires a multi-step authortisation, or    * where the vendor will take actions that result in a deviation from the purchase face value, for example     * the municipality deducts account arrears from the pre-paid electricity purchase     * ...
     *
     * @throws RestClientException if the Api call fails
     */
    @Test
    public void authPurchaseTest() {
        String id = "1";

        TerminalData terminalData = new TerminalData();

        PurchaseAuthorisationRequest purchaseAuthorisationRequest = new PurchaseAuthorisationRequest();
        purchaseAuthorisationRequest.setAuthDecisionReference("AuthReference1");
        purchaseAuthorisationRequest.setPurchaseReference("PurchaseReference1");
        purchaseAuthorisationRequest.setTerminalData(terminalData);
        purchaseAuthorisationRequest.setAuthDecision(PurchaseAuthorisationRequest.AuthDecisionEnum.ACCEPT);

        try {
            PurchaseResponse response = purchasesApi.authPurchase(id, purchaseAuthorisationRequest);
            String txRef = response.getTransactionRef();

            Long authAmount = response.getAuthorisationRequest().getAmount();
            response.getAuthorisationRequest().getStatusCode();
            response.getAuthorisationRequest().getStatusMessage();

        } catch (RestClientException apie) {
            fail("Error occurred");
        }
    }

    /**
     * Purchase Product
     * <p>
     * Make a purchase of the selected product.     * If the purchase is fully prosessed      * transactionStatus &#x3D; FULLY_PROCESSED     * transactionDetails is populated                 * If authorisation is required     * transactionStatus &#x3D; AUTH_REQUIRED     * authorisationRequest is populated     * transactionDetails field is not returned
     *
     * @throws RestClientException if the Api call fails
     */
    @Test
    public void createPurchaseTest() throws Exception {

        TerminalData terminalData = new TerminalData();
        terminalData.setChainID("ChainID1");
        terminalData.setExtraText("ExtraText1");
        terminalData.setGroupID("GroupId1");

        PurchaseTransaction purchaseTransaction = new PurchaseTransaction();
        purchaseTransaction.setAmount(100L);
        purchaseTransaction.setCatalogueProductID("catalogProdId");
        purchaseTransaction.setClientTransactionReference("ClientTransactionRef1");

        PurchaseRequest purchaseRequest = new PurchaseRequest();
        purchaseRequest.setTerminalData(terminalData);
        purchaseRequest.setTransactionDetails(purchaseTransaction);

        String rawData = "";
        Long startTime = System.currentTimeMillis();
        Long duration = 0L;
        int responseCode = -1;
        PurchaseResponse response = null;

        try {
            response = purchasesApi.createPurchase(purchaseRequest);


        } catch (RestClientException apie) {
            fail("Error occurred");


        } finally {
            //log.info("Request responseData is : " + rawData + " and responseCode : " + responseCode + " in time of : " + duration);
        }

        String responseTransactionRef = response.getTransactionRef();
        AuthorisationRequestDetails responseAuthorisationRequest = response.getAuthorisationRequest();

        assertEquals("eec97aff-770d-4ae1-a50e-02e9fb73a667", responseAuthorisationRequest.getId());

        PurchaseTransaction responseTransactionDetails = response.getTransactionDetails();
        PurchaseResponse.TransactionStatusEnum responseTransactionStatus = response.getTransactionStatus();

        /*
        result = {PurchaseResponse@13524} "class PurchaseResponse {\n    transactionDetails: class PurchaseTransaction {\n        dateTime: null\n        amount: 100\n        vendorReference: 2631105\n        reprintIndicator: true\n        deviceReference: class PurchaseTransactionDeviceReference {\n            deviceId: 27826756245\n            properties: null\n        }\n        catalogueProductName: null\n        vendorTaxReferenceNumber: null\n        clientTransactionReference: ClientTransactionRef1\n        vendorName: MTN\n        vouchers: [class PurchaseTransactionVouchers {\n            tarrifBlocks: null\n            amount: 8500\n            voucherSeq: null\n            voucherReferenceNumber: null\n            voucherType: null\n            name: null\n            description: null\n            currency: ZAR\n            receipt: null\n            expiry: null\n            taxAmount: null\n        }]\n        statusMessage: null\n        charges: null\n        vendorMessage: null\n        receipt: class TransactionReceipt {\n            recei"
 transactionDetails = {PurchaseTransaction@13547} "class PurchaseTransaction {\n    dateTime: null\n    amount: 100\n    vendorReference: 2631105\n    reprintIndicator: true\n    deviceReference: class PurchaseTransactionDeviceReference {\n        deviceId: 27826756245\n        properties: null\n    }\n    catalogueProductName: null\n    vendorTaxReferenceNumber: null\n    clientTransactionReference: ClientTransactionRef1\n    vendorName: MTN\n    vouchers: [class PurchaseTransactionVouchers {\n        tarrifBlocks: null\n        amount: 8500\n        voucherSeq: null\n        voucherReferenceNumber: null\n        voucherType: null\n        name: null\n        description: null\n        currency: ZAR\n        receipt: null\n        expiry: null\n        taxAmount: null\n    }]\n    statusMessage: null\n    charges: null\n    vendorMessage: null\n    receipt: class TransactionReceipt {\n        receiptLines: null\n        recieptDef: null\n    }\n    id: eec97aff-770d-4ae1-a50e-02e9fb73a667\n    catalogueProductID: catalogProdId\n    barcode: null\n    statusCode: 0\n}"
  dateTime = null
  amount = {Long@13554} 100
  vendorReference = "2631105"
  reprintIndicator = {Boolean@13556} true
  deviceReference = {PurchaseTransactionDeviceReference@13557} "class PurchaseTransactionDeviceReference {\n    deviceId: 27826756245\n    properties: null\n}"
  catalogueProductName = null
  vendorTaxReferenceNumber = null
  clientTransactionReference = "ClientTransactionRef1"
  vendorName = "MTN"
  vouchers = {ArrayList@13560}  size = 1
  statusMessage = null
  charges = null
  vendorMessage = null
  receipt = {TransactionReceipt@13561} "class TransactionReceipt {\n    receiptLines: null\n    recieptDef: null\n}"
  id = "eec97aff-770d-4ae1-a50e-02e9fb73a667"
  catalogueProductID = "catalogProdId"
  barcode = null
  statusCode = {Integer@13564} 0
 transactionStatus = null
 authorisationRequest = null
 transactionRef = null
         */
    }

    /**
     * Get Purchase Details
     * <p>
     * Retrieve purchase details.
     *
     * @throws RestClientException if the Api call fails
     */
    @Test
    public void getPurchaseTest() {
        String id = "!";

        try {
            PurchaseDetailsResponse response = purchasesApi.getPurchase(id);

            PurchaseAuthorisationRequest responseAuthorisation = response.getAuthorisation();
            PurchaseAuthorisationRequest.AuthDecisionEnum authDecisionEnum = responseAuthorisation.getAuthDecision();
            String authDecisionReference = responseAuthorisation.getAuthDecisionReference();


            PurchaseResponse responsePurchaseResponse = response.getPurchaseResponse();
            PurchaseResponse.TransactionStatusEnum purchaseResponseTransactionStatus = responsePurchaseResponse.getTransactionStatus();
            String responsePurchaseResponseTransactionRef = responsePurchaseResponse.getTransactionRef();
            PurchaseTransaction responsePurchaseResponseTransactionDetails = responsePurchaseResponse.getTransactionDetails();
        } catch (RestClientException apie) {
            fail("Error occurred");
        }

    }

    /**
     * Purchase Reprint
     * <p>
     * Retrieve purchase response message for previously purchased product.    This is primarily to be used as an error recovery mechanism in the event of a communication failure  or timeout during a purchase operation.
     *
     * @throws RestClientException if the Api call fails
     */
    @Test
    public void reprintReceiptTest() {
        String id = "1";

        TerminalData terminalData = new TerminalData();
        terminalData.setGroupID("groupId");
        terminalData.setExtraText("ExtraText");
        terminalData.setChainID("ChainId");

        PurchaseReprintRequest purchaseReprintRequest = new PurchaseReprintRequest();

        purchaseReprintRequest.setTerminalData(terminalData);

        try {
            PurchaseResponse response = purchasesApi.reprintReceipt(id, purchaseReprintRequest);
            PurchaseTransaction responseTransactionDetails = response.getTransactionDetails();
            String responseTransactionRef = response.getTransactionRef();
            PurchaseResponse.TransactionStatusEnum responseTransactionStatus = response.getTransactionStatus();
        } catch (RestClientException apie) {
            fail("Error occurred");
        }
    }

}
