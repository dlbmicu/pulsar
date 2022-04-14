package com.selflearning.pulsar;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouhao
 * @date 2022/4/14
 */
public class Producer {
    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .connectionTimeout(30, TimeUnit.SECONDS)
                .build();
        org.apache.pulsar.client.api.Producer<String> stringProducer = client.newProducer(Schema.STRING)
                .topic("my-topic")
                .create();
        stringProducer.send("My message");
    }
}
