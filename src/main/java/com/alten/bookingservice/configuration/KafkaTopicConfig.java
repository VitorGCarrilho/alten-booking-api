package com.alten.bookingservice.configuration;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Value(value = "${bootstrap.servers}")
    private String bootstrapAddress;

    @Value(value = "${topic.booking.request.name}")
    private String bookingRequestTopicName;

    @Value(value = "${topic.booking.request.partitions}")
    private int bookingRequestTopicPartitions;

    @Value(value = "${topic.booking.request.replication}")
    private int bookingRequestTopicReplication;

    @Value(value = "${topic.booking.cancel.name}")
    private String cancelRequestTopicName;

    @Value(value = "${topic.booking.cancel.partitions}")
    private int cancelRequestTopicPartitions;

    @Value(value = "${topic.booking.cancel.replication}")
    private int cancelRequestTopicReplication;

    @Value(value = "${topic.booking.update.name}")
    private String updateRequestTopicName;

    @Value(value = "${topic.booking.update.partitions}")
    private int updateRequestTopicPartitions;

    @Value(value = "${topic.booking.update.replication}")
    private int updateRequestTopicReplication;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic bookingRequestTopic() {
        return new NewTopic(bookingRequestTopicName, bookingRequestTopicPartitions, (short) bookingRequestTopicReplication);
    }

    @Bean
    public NewTopic cancelBookingRequestTopic() {
        return new NewTopic(cancelRequestTopicName, cancelRequestTopicPartitions, (short) cancelRequestTopicReplication);
    }

    @Bean
    public NewTopic updateBookingRequestTopic() {
        return new NewTopic(updateRequestTopicName, updateRequestTopicPartitions, (short) updateRequestTopicReplication);
    }
}
