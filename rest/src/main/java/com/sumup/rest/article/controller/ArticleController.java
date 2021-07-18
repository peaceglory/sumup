package com.sumup.rest.article.controller;

import com.sumup.rest.article.dto.request.ArticleRequest;
import com.sumup.rest.article.dto.response.ArticleResponse;
import com.sumup.rest.article.service.ArticleService;
import com.sumup.rest.article.util.ConverterUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final ConverterUtil mapArticle;

    public ArticleController(ArticleService articleService, ConverterUtil mapArticle) {
        this.articleService = articleService;
        this.mapArticle = mapArticle;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ArticleResponse> postArticle(@Valid @RequestBody ArticleRequest articleRequest)
                                                       throws Exception {
        final ArticleResponse response = articleService.process(mapArticle.from(articleRequest));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
