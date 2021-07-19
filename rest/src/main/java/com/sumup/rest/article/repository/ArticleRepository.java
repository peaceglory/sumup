package com.sumup.rest.article.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumup.rest.article.Article;
import com.sumup.rest.config.KafkaTopicConfig;
import com.sumup.rest.exception.definitions.SerializationException;
import com.sumup.rest.interfaces.repository.DataAccessRepository;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

import java.util.Objects;

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

    public Article push(Article article) {
        final String articleAsString;

        try {
            articleAsString = objectMapper.writeValueAsString(article);
        } catch (JsonProcessingException e) {
            throw new SerializationException("Serializing article " + article.getTitle() + " failed!", e);
        }

        kafkaTemplate.send(topicName, article.getTitle(), articleAsString)
            .addCallback(result -> {
                if (LOG.isDebugEnabled() && Objects.nonNull(result)) {
                    final RecordMetadata recordMetadata = result.getRecordMetadata();

                    LOG.debug("Successful push! \n" +
                            "\t\tTopic: {}\n" +
                            "\t\tPartition: {}\n" +
                            "\t\tOffset: {}\n" +
                            "\t\t\n", recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
                }
            }, ex -> LOG.error("Error while pushing record!", ex));

        return article;
    }
}
