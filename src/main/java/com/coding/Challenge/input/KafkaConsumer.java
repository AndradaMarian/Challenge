package com.coding.Challenge.input;

import com.coding.Challenge.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "user-topic", groupId = "user-group")
    public void consume(User user) {
        System.out.println("Consumed user: " + user.getEmail());
    }
}
