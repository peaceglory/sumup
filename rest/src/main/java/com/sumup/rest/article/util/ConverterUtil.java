package com.sumup.rest.article.util;

import com.sumup.rest.article.dto.request.ArticleRequest;
import com.sumup.rest.article.dto.response.ArticleResponse;
import com.sumup.rest.article.Article;
import org.springframework.stereotype.Component;

@Component
public class ConverterUtil {

    public Article from(ArticleRequest articleRequest) {
        return new Article(articleRequest.getTitle(), articleRequest.getGroup(), articleRequest.getText());
    }

    public ArticleResponse from(Article article) {
        return new ArticleResponse(article.getTitle(), article.getGroup(), article.getText());
    }
}
