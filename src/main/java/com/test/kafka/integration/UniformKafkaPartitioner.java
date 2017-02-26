package com.test.kafka.integration;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mohammad.tasnim on 04/08/16.
 */
@Slf4j
public class UniformKafkaPartitioner implements Partitioner {
    final AtomicInteger counter = new AtomicInteger(0);

    public UniformKafkaPartitioner (VerifiableProperties props) {
        log.info("Instantiated Uniform Kafka Partitioner class");
    }
    public int partition(Object key, int numPartitions) {
        if(counter.get() >= Integer.MAX_VALUE)
            counter.set(0);
        int partitionID = counter.incrementAndGet() % numPartitions;

        log.debug("partitionid: " + partitionID);
        return partitionID;

    }

}
