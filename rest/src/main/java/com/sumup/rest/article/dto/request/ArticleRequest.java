package com.sumup.rest.article.dto.request;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

//@Validated
public final class ArticleRequest {
    @NotBlank
    private final String title;

    @NotBlank
    private final String group;

    @NotBlank
    private final String text;

    public ArticleRequest(String title, String group, String text) {
        this.title = title;
        this.group = group;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getGroup() {
        return group;
    }

    public String getText() {
        return text;
    }
}
