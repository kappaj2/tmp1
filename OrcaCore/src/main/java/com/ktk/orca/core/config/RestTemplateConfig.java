package com.ktk.orca.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class RestTemplateConfig {

    @Autowired
    private ApplicationProperties props;
    @Autowired
    private RestTemplateResponseErrorHandler restTemplateResponseErrorHandler;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        RestTemplate restTemplate = builder.build();

        List<ClientHttpRequestInterceptor> interceptors
                = restTemplate.getInterceptors();

        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }

        interceptors.add(new CustomClientHttpRequestInterceptor(props));

        restTemplate.setInterceptors(interceptors);
        restTemplate.setErrorHandler(restTemplateResponseErrorHandler);

        return restTemplate;
    }

    @Bean
    public RestTemplateCustomizer restTemplateCustomizer() {
        return restTemplate ->
                restTemplate.setRequestFactory(clientHttpRequestFactory());
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {

        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();

        clientHttpRequestFactory.setConnectTimeout(props.getRestTemplateConfig().getConnectTimeout());
        clientHttpRequestFactory.setReadTimeout(props.getRestTemplateConfig().getReadTimeout());
        clientHttpRequestFactory.setBufferRequestBody(true);
        clientHttpRequestFactory.setOutputStreaming(false);

        return new BufferingClientHttpRequestFactory(clientHttpRequestFactory);

    }
}
