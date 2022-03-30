package com.example;

import com.example.dto.MessageAggregate;
import reactor.core.publisher.Mono;

public interface ProducerService {
    Mono<Void> produceMessage(MessageAggregate message);
}
