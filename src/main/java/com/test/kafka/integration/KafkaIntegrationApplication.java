package com.test.kafka.integration;

import com.datasift.dropwizard.kafka.KafkaConsumerFactory;
import com.datasift.dropwizard.kafka.consumer.KafkaConsumer;
import com.datasift.dropwizard.kafka.serializer.JacksonDecoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.kafka.integration.config.ApplicationConfig;
import com.test.kafka.integration.processor.KafkaProcessor;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * @author vishnu.agarwal
 *
 */
public class KafkaIntegrationApplication extends Application<ApplicationConfig> {

    public static void main(String[] args) throws Exception {
        new KafkaIntegrationApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ApplicationConfig> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public void run(ApplicationConfig configuration, Environment environment) throws Exception {
        final ObjectMapper objectMapper = environment.getObjectMapper();
        final JacksonDecoder<String> keyDecoder = new JacksonDecoder<>(objectMapper, String.class);
        final JacksonDecoder<String> valueDecoder = new JacksonDecoder<>(objectMapper, String.class);
        KafkaProcessor processor=new KafkaProcessor();
        final KafkaConsumerFactory kafkaConsumerFactory = configuration.getKafkaConsumerFactory();
        final KafkaConsumer kafkaConsumer = kafkaConsumerFactory
                .processWith(keyDecoder, valueDecoder, processor).build(environment);
    }
}
