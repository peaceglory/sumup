package com.sumup.entity;

import java.util.Objects;

public final class Article {
    private String title;
    private String group;
    private String text;

    public Article() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setText(String text) {
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
