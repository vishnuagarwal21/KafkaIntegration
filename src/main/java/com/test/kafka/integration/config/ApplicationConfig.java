package com.test.kafka.integration.config;

import javax.validation.Valid;

import com.datasift.dropwizard.kafka.KafkaConsumerFactory;

import io.dropwizard.Configuration;
import lombok.Data;

@Data
public class ApplicationConfig extends Configuration {

    @Valid
    private KafkaConsumerFactory kafkaConsumerFactory = new KafkaConsumerFactory();
}
