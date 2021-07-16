package com.sumup.preprocessor;

import com.sumup.config.ConfigService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public abstract class AbstractPreprocessor {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractPreprocessor.class);

    private final ConfigService config;

    protected AbstractPreprocessor(ConfigService config) {
        this.config = config;
    }

    protected abstract Topology preprocess();

    public void buildAndStartTopology() {
        final Properties streamsConfig = createStreamsConfig(config);

        final Topology topology = preprocess();

        final KafkaStreams kafkaStreams = startTopology(streamsConfig, topology);

        addShutdownHook(kafkaStreams);
    }

    private void addShutdownHook(KafkaStreams kafkaStreams) {
        // Close stream and release resources on shutting down
        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));

        // Print the topology
        kafkaStreams.localThreadsMetadata().forEach(data -> LOG.info(data.toString()));
    }

    private KafkaStreams startTopology(Properties streamsConfig, Topology topology) {
        final KafkaStreams kafkaStreams = new KafkaStreams(topology, streamsConfig);
        kafkaStreams.start();
        return kafkaStreams;
    }

    private Properties createStreamsConfig(ConfigService config) {
        final Properties properties = new Properties();

        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, config.streams().defaultKeySerde());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, config.streams().defaultValueSerde());
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, config.streams().applicationId());
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, config.streams().kafkaBroker());

        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, config.streams().consumer().autoOffsetReset());

        return properties;
    }
}
