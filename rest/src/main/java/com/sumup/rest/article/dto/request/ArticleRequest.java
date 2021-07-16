package com.sumup.rest.article.dto.request;

public class ArticleRequest {
    private final String title;
    private final String group;
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
