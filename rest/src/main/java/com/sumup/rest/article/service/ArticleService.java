package com.sumup.rest.article.service;

import com.sumup.rest.article.dto.response.ArticleResponse;
import com.sumup.rest.article.Article;
import com.sumup.rest.article.repository.ArticleRepository;
import com.sumup.rest.article.util.ConverterUtil;
import com.sumup.rest.exception.definitions.DataAccessException;
import com.sumup.rest.exception.definitions.ProcessingException;
import com.sumup.rest.interfaces.service.ProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ArticleService implements ProcessingService<Article, ArticleResponse> {
    private static final Logger LOG = LoggerFactory.getLogger(ArticleService.class);

    private final ArticleRepository repository;
    private final ConverterUtil converter;

    public ArticleService(ArticleRepository repository, ConverterUtil converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public ArticleResponse process(Article article) throws ProcessingException {
        final Article pushed;
        try {
            pushed = repository.push(article);
        } catch (DataAccessException e) {
            final String message = "Processing article " + article.getTitle() + " failed";
            LOG.error(message, e);
            throw new ProcessingException(message);
        }
        return converter.from(pushed);
    }

}
