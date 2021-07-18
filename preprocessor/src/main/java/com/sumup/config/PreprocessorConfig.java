package com.sumup.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Profile(SpringConfig.ARTICLE_PREPROCESSOR)
@ConfigurationProperties(prefix = "preprocessor")
public class PreprocessorConfig {
    private String applicationId;
    private Set<String> keywords;
    private Set<String> delimiters;
    private Set<String> common;

    public String applicationId() {
        return applicationId;
    }

    public Set<String> delimiters() {
        return delimiters;
    }

    public Set<String> keywords() {
        return keywords;
    }

    public Set<String> common() {
        return common;
    }

    void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    void setCommon(Set<String> common) {
        this.common = common;
    }

    void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    void setDelimiters(Set<String> delimiters) {
        this.delimiters = delimiters;
    }
}
