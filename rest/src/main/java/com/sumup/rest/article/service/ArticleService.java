package com.sumup.rest.article.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumup.rest.article.dto.response.ArticleResponse;
import com.sumup.rest.article.Article;
import com.sumup.rest.article.repository.ArticleRepository;
import com.sumup.rest.article.util.ConverterUtil;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private final ArticleRepository repository;
    private final ObjectMapper objectMapper;
    private final ConverterUtil converter;

    public ArticleService(ArticleRepository repository, ObjectMapper objectMapper, ConverterUtil converter) {
        this.repository = repository;
        this.objectMapper = objectMapper;
        this.converter = converter;
    }

    public ArticleResponse write(Article article) throws JsonProcessingException {
        final String articleAsString = objectMapper.writeValueAsString(article);
        repository.push(article.getTitle(), articleAsString);
        return converter.from(article);
    }

}
