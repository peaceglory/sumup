package com.sumup.rest.article.dto.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

import static com.sumup.rest.article.constants.ValidationConstants.*;

public final class ArticleRequest {
    @NotBlank(message = TITLE_BLANK_MSG)
    @Length(min = TITLE_MIN, max = TITLE_MAX, message = TITLE_LENGTH_MSG)
    private final String title;

    @NotBlank(message = GROUP_BLANK_MSG)
    @Length(min = GROUP_MIN, max = GROUP_MAX, message = GROUP_LENGTH_MSG)
    private final String group;

    @NotBlank(message = TEXT_BLANK_MSG)
    @Length(min = TEXT_MIN, max = TEXT_MAX, message = TEXT_LENGTH_MSG)
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
