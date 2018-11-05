package com.ktk.orca.core.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = true)
public class ApplicationProperties {

    @Value("${application.fname}") private String propFile;
    public String getPropFile(){
        return propFile;
    }

    public final OrcaApi orcaApi = new OrcaApi();

    public final RestTemplateConfig restTemplateConfig = new RestTemplateConfig();

    public RestTemplateConfig getRestTemplateConfig() {
        return restTemplateConfig;
    }

    public OrcaApi getOrcaApi() {
        return orcaApi;
    }

    @Data
    public static class OrcaApi {
        private String baseUri;
        private String accessToken;
        private String authUri;
    }

    @Data
    public static class RestTemplateConfig {
        private Integer connectTimeout;
        private Integer readTimeout;
    }
}
