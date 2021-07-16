package com.sumup.rest.article.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sumup.rest.article.dto.request.ArticleRequest;
import com.sumup.rest.article.dto.response.ArticleResponse;
import com.sumup.rest.article.service.ArticleService;
import com.sumup.rest.article.util.ConverterUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticlesController {
    private final ArticleService articleService;
    private final ConverterUtil mapper;

    public ArticlesController(ArticleService articleService, ConverterUtil mapper) {
        this.articleService = articleService;
        this.mapper = mapper;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ArticleResponse postArticle(@RequestBody ArticleRequest articleRequest) throws JsonProcessingException {
        return articleService.write(mapper.from(articleRequest));
    }
}
