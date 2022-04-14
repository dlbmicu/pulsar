package com.selflearning.pulsar;

import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouhao
 * @date 2022/4/14
 */
public class Consumer {
    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .connectionTimeout(30, TimeUnit.SECONDS)
                .build();
        org.apache.pulsar.client.api.Consumer consumer = client.newConsumer()
                .topic("my-topic")
                .subscriptionName("my-subscription")
                .subscribe();
        while (true) {
            // Wait for a message
            Message msg = consumer.receive();
            try {
                // Do something with the message
                System.out.println("Message received: " + new String(msg.getData()));
                // Acknowledge the message so that it can be deleted by the message broker
                consumer.acknowledge(msg);
            } catch (Exception e) {
                // Message failed to process, redeliver later
                consumer.negativeAcknowledge(msg);
            }
        }
    }
}
