package com.sumup.config;

import org.apache.kafka.common.serialization.Serdes;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "streams")
public class StreamsConfig {
    private final Class<?> defaultKeySerde = Serdes.String().getClass();
    private final Class<?> defaultValueSerde = Serdes.String().getClass();

    private String kafkaBroker;
    private String applicationId;
    private Consumer consumer;
    private Producer producer;

    public static final class Consumer {
        @NestedConfigurationProperty
        private Topic topic;

        private String autoOffsetReset;

        public Topic topic() {
            return topic;
        }

        public String autoOffsetReset() {
            return autoOffsetReset;
        }

        void setTopic(Topic topic) {
            this.topic = topic;
        }

        void setAutoOffsetReset(String autoOffsetReset) {
            this.autoOffsetReset = autoOffsetReset;
        }
    }

    public static class Producer {
        @NestedConfigurationProperty
        private Topic topic;

        public Topic topic() {
            return topic;
        }

        void setTopic(Topic topic) {
            this.topic = topic;
        }
    }

    public static class Topic {
        private String name;
        private int partitions;

        public String name() {
            return name;
        }

        public int partitions() {
            return partitions;
        }

        void setName(String name) {
            this.name = name;
        }

        void setPartitions(int partitions) {
            this.partitions = partitions;
        }
    }

    public String kafkaBroker() {
        return kafkaBroker;
    }

    public String applicationId() {
        return applicationId;
    }

    public Class<?> defaultKeySerde() {
        return defaultKeySerde;
    }

    public Class<?> defaultValueSerde() {
        return defaultValueSerde;
    }

    public Consumer consumer() {
        return consumer;
    }

    public Producer producer() {
        return producer;
    }

    void setKafkaBroker(String kafkaBroker) {
        this.kafkaBroker = kafkaBroker;
    }

    void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    void setProducer(Producer producer) {
        this.producer = producer;
    }
}
