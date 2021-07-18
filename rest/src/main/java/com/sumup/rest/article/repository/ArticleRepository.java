package com.sumup.rest.article.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumup.rest.article.Article;
import com.sumup.rest.config.KafkaTopicConfig;
import com.sumup.rest.exception.definitions.DataAccessException;
import com.sumup.rest.interfaces.repository.DataAccessRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepository implements DataAccessRepository<Article> {
    private static final Logger LOG = LoggerFactory.getLogger(ArticleRepository.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName;
    private final ObjectMapper objectMapper;


    public ArticleRepository(KafkaTemplate<String, String> kafkaTemplate,
                             KafkaTopicConfig topicConfig,
                             ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicConfig.getTopicName();
        this.objectMapper = objectMapper;
    }

    public Article push(Article article) throws DataAccessException {
        final String articleAsString;
        try {
            articleAsString = objectMapper.writeValueAsString(article);
        } catch (JsonProcessingException e) {
            final String message = "Persisting article " + article.getTitle() + " failed";
            LOG.error(message, e);
            throw new DataAccessException(message);
        }
        kafkaTemplate.send(topicName, article.getTitle(), articleAsString);
        return article;
    }
}
