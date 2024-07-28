package com.example.temporal.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "access-events", groupId = "temporal-group")
    public void listenAccessEvents(String message) {
        // Handle access events and update workflow state
    }

    @KafkaListener(topics = "order-events", groupId = "temporal-group")
    public void listenOrderEvents(String message) {
        // Handle order events and update workflow state
    }

    @KafkaListener(topics = "campaign-events", groupId = "temporal-group")
    public void listenCampaignEvents(String message) {
        // Handle campaign events and update workflow state
    }
}
