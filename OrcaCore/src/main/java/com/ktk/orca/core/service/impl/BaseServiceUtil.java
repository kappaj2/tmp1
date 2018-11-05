package com.ktk.orca.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ktk.orca.core.internalpojos.ApiStatistics;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

@Slf4j
@Component
public class BaseServiceUtil {

    private ObjectMapper objectMapper;
    private JmsTemplate jmsTemplate;

    public BaseServiceUtil(@Qualifier("jsonObjectMapper") ObjectMapper objectMapper, JmsTemplate jmsTemplate) {
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Build and ApiException object using the information provided. This is the result of a RestTemplate call failure.
     *
     * @param rce
     * @param responseCode
     * @return
     */
    protected ApiError buildBaseException(RestClientException rce, int responseCode) {

        ApiError apiError = ApiError.builder()
                .errors(new ArrayList<>())
                .message(rce.getLocalizedMessage())
                .statusCode(responseCode)
                .build();

        return apiError;
    }

    /**
     * Utility method to parse the object to a String.
     * This swallows the exception and returns an empty String. This is acceptable as the result string is used for statistics only.
     * The error is logged as an error as well to provide feedback so it can be resolved.
     *
     * @param inputObject
     * @return
     */
    protected String parseObjectToString(Object inputObject) {

        String response = "";
        try {
            response = objectMapper.writeValueAsString(inputObject);
        } catch (JsonProcessingException jpe) {
            log.error("Error processing the object. Failing to write the object as String. " + jpe.getMessage());
        }
        return response;
    }

    /**
     * Create a new object node.
     * @return
     */
    protected ObjectNode createNewObjectNode(){
       return objectMapper.createObjectNode();
    }

    /**
     * Create a new Statics object and submit to JMS queue for writing to the database.
     * @param apiName
     * @param apiMethod
     * @param startTime
     * @param requestPayload
     * @param responseCode
     * @param responseMessage
     * @param responsePayload
     */
    protected void writeTransactionRecord(final String apiName,
                                          final String apiMethod,
                                          final Instant startTime,
                                          final Object requestPayload,
                                          final Integer responseCode,
                                          final String responseMessage,
                                          final Object responsePayload){

        Instant endTime = Instant.now();
        long callDuration = Duration.between(startTime, endTime).toMillis();

        MDC.put("httpStatusCode", String.valueOf(responseCode));
        MDC.put("responseMessage", responseMessage == null ? "" : responseMessage);
        log.info("Submitted api stats");
        MDC.clear();

        ApiStatistics stats = ApiStatistics.builder()
                .apiCalled(apiName)
                .apiMethodCalled(apiMethod)
                .callDuration(callDuration)
                .requestPayload(parseObjectToString(requestPayload))
                .responseCode(responseCode)
                .responseMessage(responseMessage)
                .apiRequestTime(Instant.now())
                .responsePayload(parseObjectToString(responsePayload))
                .build();

        jmsTemplate.convertAndSend("ApiStatistics", stats);
    }
}
