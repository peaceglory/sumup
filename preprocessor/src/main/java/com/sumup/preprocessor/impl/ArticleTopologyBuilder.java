package com.sumup.preprocessor.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sumup.config.ConfigService;
import com.sumup.config.SpringConfig;
import com.sumup.entity.Article;
import com.sumup.preprocessor.AbstractTopologyBuilder;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Profile(SpringConfig.ARTICLE_PREPROCESSOR)
public final class ArticleTopologyBuilder extends AbstractTopologyBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(ArticleTopologyBuilder.class);

    private final String inputTopic;
    private final String outputTopic;
    private final String tokenizingPattern;
    private final Set<String> keywords;
    private final Set<String> common;

    public ArticleTopologyBuilder(final ConfigService config) {
        super(config);

        this.inputTopic = config.streams().consumer().topic().name();
        this.outputTopic = config.streams().producer().topic().name();
        this.tokenizingPattern = "[" + String.join("", config.preprocessor().delimiters()) + "]+";
        this.keywords = config.preprocessor().keywords();
        this.common = config.preprocessor().common();
    }


    @Override
    public Topology buildTopology() {
        final StreamsBuilder builder = new StreamsBuilder();

        final KStream<String, String> input = builder.stream(inputTopic);

        input.filter(notContaining(keywords)).to(outputTopic);

        return builder.build();
    }

    private Predicate<String, String> notContaining(final Set<String> keywords) {
        return (key, value) -> {
            try {
                final Article article = mapper.readValue(value, Article.class);

                Objects.requireNonNull(article.getText());

                final String[] split = article.getText().split(tokenizingPattern);
                final Set<String> articleUniqueWords = Stream.of(split)
                        .map(String::toLowerCase)
                        // Don't include common words
                        .filter(word -> !common.contains(word))
                        // Discard duplicates
                        .collect(Collectors.toCollection(HashSet::new));

                return keywords.stream().noneMatch(articleUniqueWords::contains);
            } catch (JsonProcessingException e) {
                LOG.error("Error processing input: {}", e.getMessage());
            }
            return false;
        };
    }
}
