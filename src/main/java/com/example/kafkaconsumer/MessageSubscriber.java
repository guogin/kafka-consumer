package com.example.kafkaconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageSubscriber {
    private final MessageRepository repository;

    private static Logger logger = LoggerFactory.getLogger(MessageSubscriber.class);

    @Autowired
    public MessageSubscriber(MessageRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(id = "myId", topics = "topic1")
    public void listen(String in) {
        logger.info("Received message [" + in + "]");

        Message message = new Message();
        message.setContent(in);
        repository.save(message);
    }
}
