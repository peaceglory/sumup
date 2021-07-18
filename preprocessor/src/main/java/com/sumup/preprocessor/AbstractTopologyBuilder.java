package com.sumup.preprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumup.config.ConfigService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public abstract class AbstractTopologyBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractTopologyBuilder.class);

    private final ConfigService config;

    protected final ObjectMapper mapper;

    protected AbstractTopologyBuilder(final ConfigService config) {
        this.config = config;
        this.mapper = config.spring().mapper();
    }

    protected abstract Topology buildTopology();

    public void buildAndStartTopology() {
        final Properties streamsConfig = createStreamsConfig(config);

        final Topology topology = buildTopology();

        final KafkaStreams kafkaStreams = startTopology(topology, streamsConfig);

        addShutdownHook(kafkaStreams);
    }

    private void addShutdownHook(final KafkaStreams kafkaStreams) {
        // Close stream and release resources on shutting down
        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));

        // Print the topology
        kafkaStreams.localThreadsMetadata().forEach(data -> LOG.info(data.toString()));
    }

    private KafkaStreams startTopology(final Topology topology, final Properties streamsConfig) {
        final KafkaStreams kafkaStreams = new KafkaStreams(topology, streamsConfig);
        kafkaStreams.start();
        return kafkaStreams;
    }

    private Properties createStreamsConfig(final ConfigService config) {
        final Properties properties = new Properties();

        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, config.streams().defaultKeySerde());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, config.streams().defaultValueSerde());
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, config.preprocessor().applicationId());
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, config.streams().kafkaBroker());

        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, config.streams().consumer().autoOffsetReset());

        return properties;
    }
}
