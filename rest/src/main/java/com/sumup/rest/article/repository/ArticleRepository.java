package com.sumup.rest.article.repository;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepository {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ArticleRepository(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void push(String primaryKey, String data) {
        kafkaTemplate.send("all_messages", primaryKey, data);
    }
}
