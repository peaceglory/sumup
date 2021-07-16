package com.sumup;

import com.sumup.preprocessor.AbstractPreprocessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PreprocessorApplication implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(PreprocessorApplication.class);

    private final AbstractPreprocessor preprocessor;

    public PreprocessorApplication(AbstractPreprocessor preprocessor) {
        this.preprocessor = preprocessor;
    }

    public static void main(String[] args) {
        SpringApplication.run(PreprocessorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        preprocessor.buildAndStartTopology();
    }

}
