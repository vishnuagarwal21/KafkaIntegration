package com.test.kafka.integration.config;

import com.datasift.dropwizard.kafka.consumer.MessageProcessor;

public class KafkaProcessor extends MessageProcessor<String, String> {

    @Override
    public void process(String key, String message, String topic, int partition, long offset) {
        
    }

}
