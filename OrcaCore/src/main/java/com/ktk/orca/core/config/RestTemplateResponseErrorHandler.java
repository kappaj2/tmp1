package com.ktk.orca.core.config;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktk.orca.core.exceptions.RestTemplateError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Slf4j
@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Autowired
    @Qualifier("jsonObjectMapper")
    private ObjectMapper objectMapper;

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return (

                httpResponse.getStatusCode().series() == CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {

        if (httpResponse.getStatusCode()
                .series() == HttpStatus.Series.SERVER_ERROR) {

            Integer responseCode = httpResponse.getRawStatusCode();
            String errorMessages = httpResponse.getStatusText();
            ApiError apiError = new ApiError(responseCode, errorMessages, "");

            throw new RestTemplateError(errorMessages, apiError);

            // handle SERVER_ERROR
        } else if (httpResponse.getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR) {
            // handle CLIENT_ERROR
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new IOException();
            }


            InputStream is = httpResponse.getBody();

            String inputString = bodyToString(is);
            JsonNode node = objectMapper.readTree(inputString);

            List<String> errors = new ArrayList<>();

            if (node.get("loginState") != null) {
                errors.add("loginState:" + node.get("loginState").asText());
            }

            if (node.get("flowCode") != null) {
                errors.add("flowCode:" + node.get("flowCode").asText());
            }

            if (node.get("userId") != null) {
                errors.add("userId:" + node.get("userId").asText());
            }

            if (node.get("message") != null) {
                errors.add("message:" + node.get("message").asText());
            }

            Integer responseCode = httpResponse.getRawStatusCode();
            String errorMessages = httpResponse.getStatusText();
            ApiError apiError = new ApiError(responseCode, errorMessages, errors);

            throw new RestTemplateError(errorMessages, apiError);

        }
    }

    private String bodyToString(InputStream body) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(body, StandardCharsets.UTF_8));
        String line = bufferedReader.readLine();
        while (line != null) {
            stringBuilder.append(line).append(System.lineSeparator());
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }
}