package com.ktk.orca.core.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class TestProfiles {

//    @Bean
//    @Profile("dev")
//    public PropertySourcesPlaceholderConfigurer propertiesStage() {
//        return properties("dev");
//    }
//
//    @Bean
//    @Profile("stage")
//    public PropertySourcesPlaceholderConfigurer propertiesDev() {
//        return properties("stage");
//    }
//
//    @Bean
//    @Profile("default")
//    public PropertySourcesPlaceholderConfigurer propertiesDefault() {
//        return properties("default");
//
//    }
//
//    @Bean
//    @Profile("prod")
//    public PropertySourcesPlaceholderConfigurer propertiesProd() {
//        return properties("prod");
//
//    }
//
//    /**
//     * Update custom specific yml file with profile configuration.
//     * @param profile
//     * @return
//     */
//    public static PropertySourcesPlaceholderConfigurer properties(String profile) {
//        PropertySourcesPlaceholderConfigurer propertyConfig = null;
//        YamlPropertiesFactoryBean yaml  = null;
//
//        propertyConfig  = new PropertySourcesPlaceholderConfigurer();
//        yaml = new YamlPropertiesFactoryBean();
//        yaml.setDocumentMatchers(new SpringProfileDocumentMatcher(profile));// load profile filter.
//        yaml.setResources(new ClassPathResource("env_config/test-service-config.yml"));
//        propertyConfig.setProperties(yaml.getObject());
//        return propertyConfig;
//    }
}
