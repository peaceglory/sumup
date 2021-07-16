package com.sumup.rest.article;

import java.util.Objects;

public final class Article {
    private final String title;
    private final String group;
    private final String text;

    public Article(String title, String group, String text) {
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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || getClass() != that.getClass()) {
            return false;
        }
        final Article article = (Article) that;

        return Objects.equals(title, article.title)
                && Objects.equals(group, article.group)
                && Objects.equals(text, article.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, group, text);
    }
}
