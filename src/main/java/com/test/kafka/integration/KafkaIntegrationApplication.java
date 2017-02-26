package com.test.kafka.integration;

import com.datasift.dropwizard.kafka.KafkaConsumerFactory;
import com.datasift.dropwizard.kafka.consumer.KafkaConsumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.kafka.integration.config.ApplicationConfig;
import com.test.kafka.integration.processor.KafkaProcessor;
import com.test.kafka.integration.utils.JacksonDecoder;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * @author vishnu.agarwal 26/02/2017
 * 
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
        byte[] test=new byte[]{106, 100, 110, 100, 110, 115};
        //String s=objectMapper.readValue(test, String.class);
        final JacksonDecoder<String> keyDecoder = new JacksonDecoder<>(objectMapper, String.class);
        final JacksonDecoder<String> valueDecoder = new JacksonDecoder<>(objectMapper, String.class);
        KafkaProcessor processor=new KafkaProcessor();
        final KafkaConsumerFactory kafkaConsumerFactory = configuration.getKafkaConsumerFactory();
        final KafkaConsumer kafkaConsumer = kafkaConsumerFactory.processWith(keyDecoder, valueDecoder, processor).build(environment);
    }
}
