package com.example.producer.integration;

import com.example.core.Message;
import com.example.event.Event;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class MessageIntegration {

    private static final String MESSAGES_BINDING_NAME = "messageProducer-out-0";

    private final StreamBridge streamBridge;

    public MessageIntegration(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public Message createMessage(Message message) {
        log.info("Producing message to kafka {} ...", message);
        final Event<Integer, Message> event = new Event<>(Event.Type.CREATE, message.getMessageId(), message);
        boolean sent = streamBridge.send(MESSAGES_BINDING_NAME, event);

        if (sent) {
            log.info("Message sent with success !");
        } else {
            log.warn("Could not send message !");
        }

        return message;
    }
}
