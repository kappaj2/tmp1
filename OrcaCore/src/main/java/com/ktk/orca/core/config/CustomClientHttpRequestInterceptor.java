package com.ktk.orca.core.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
/**
 * Utility class to intercept all outgoing calls going through the RestTemplate. It will inject the required auth header and log the info.
 */
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private ApplicationProperties props;

    public CustomClientHttpRequestInterceptor(ApplicationProperties props) {
        this.props = props;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        /*
            Send the header information to Stash.   Do this BEFORE adding the auth header value.
         */
        logRequestDetails(request);

        HttpHeaders headers = request.getHeaders();

        /*
            Auth header not required for authentication related calls.
         */
        if (!request.getURI().toASCIIString().contains("/user")) {
            headers.add("x-api-key", props.getOrcaApi().getAccessToken());
        }
        return execution.execute(request, body);
    }

    private void logRequestDetails(HttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        Map<String, String> headerMap = new HashMap<>();

        headers.entrySet().stream().forEach(header ->
                headerMap.put(header.getKey(), header.getValue().toString()));

        MDC.clear();
        MDC.put("Request Headers: {}", headerMap.toString());
        MDC.put("Request Method: {}", request.getMethod().toString());
        MDC.put("Request URI: {}", request.getURI().toASCIIString());
        log.info("Header Information");
        MDC.clear();
    }
}
