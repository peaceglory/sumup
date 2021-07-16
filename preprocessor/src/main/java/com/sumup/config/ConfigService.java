package com.sumup.config;

import org.springframework.stereotype.Component;

@Component
public final class ConfigService {
    private final SpringConfig springConfig;
    private final PreprocessorConfig preprocessorConfig;
    private final StreamsConfig streamsConfig;

    public ConfigService(SpringConfig springConfig, PreprocessorConfig preprocessorConfig, StreamsConfig streamsConfig) {
        this.springConfig = springConfig;
        this.preprocessorConfig = preprocessorConfig;
        this.streamsConfig = streamsConfig;
    }

    public SpringConfig spring() {
        return springConfig;
    }

    public PreprocessorConfig preprocessor() {
        return preprocessorConfig;
    }

    public StreamsConfig streams() {
        return streamsConfig;
    }
}
