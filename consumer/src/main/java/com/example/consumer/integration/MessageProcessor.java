package com.example.consumer.integration;

import com.example.core.Message;
import com.example.event.Event;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Log4j2
@Component
public class MessageProcessor {

    @Bean
    public Consumer<Event<Integer, Message>> messageConsumer() {
        return event -> {
            log.info("Processing new message event {}", event);

            switch (event.getEventType()) {
                case CREATE -> {
                    log.info("Handling CREATE message with id {}", event.getData().getMessageId());
                }

                // Add more events ...

                default -> {
                    log.warn("Event type not supported !");
                }
            }

            log.info("End processing message !");
        };
    }

}
