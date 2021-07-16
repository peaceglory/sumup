package com.sumup.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    public static final String ARTICLE_PREPROCESSOR = "ArticlePreprocessor";

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }
}
