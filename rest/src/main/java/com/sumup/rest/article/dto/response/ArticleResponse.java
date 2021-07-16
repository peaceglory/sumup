package com.sumup.rest.article.dto.response;

public class ArticleResponse {
    private final String title;
    private final String group;
    private final String text;

    public ArticleResponse(String title, String group, String text) {
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
