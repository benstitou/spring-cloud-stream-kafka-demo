package com.example.producer.service;

import com.example.ProducerService;
import com.example.core.Message;
import com.example.dto.MessageAggregate;
import com.example.producer.integration.MessageIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service("ProducerServiceImpl")
public class ProducerServiceImpl implements ProducerService {

    private final MessageIntegration messageIntegration;

    @Autowired
    public ProducerServiceImpl(MessageIntegration messageIntegration) {
        this.messageIntegration = messageIntegration;
    }

    @Override
    public Mono<Void> produceMessage(MessageAggregate message) {
        try {
            return Mono.just(
                    messageIntegration.createMessage(new Message(message.messageId(), message.message()))
            ).then();
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
