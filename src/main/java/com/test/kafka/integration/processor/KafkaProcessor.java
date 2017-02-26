package com.test.kafka.integration.processor;

import com.datasift.dropwizard.kafka.consumer.MessageProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaProcessor extends MessageProcessor<String,String> {
    @Override
    public void process(String key, String message, String topic, int partition, long offset) {
        System.out.println(message);
    }

   

}
