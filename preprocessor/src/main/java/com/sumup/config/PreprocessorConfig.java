package com.sumup.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@ConfigurationProperties(prefix = "preprocessor")
public class PreprocessorConfig {
    private Set<String> keywords;
    private Set<String> delimiters;
    private Set<String> common;

    public Set<String> delimiters() {
        return delimiters;
    }

    public Set<String> keywords() {
        return keywords;
    }

    public Set<String> common() {
        return common;
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
