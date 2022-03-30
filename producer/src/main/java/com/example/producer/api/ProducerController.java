package com.example.producer.api;

import com.example.ProducerService;
import com.example.dto.MessageAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ProducerController {

    private final ProducerService producerService;

    @Autowired
    public ProducerController(@Qualifier("ProducerServiceImpl") ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping(value = "produce", consumes = APPLICATION_JSON_VALUE)
    public Mono<Void> produceMessage(@RequestBody MessageAggregate message) {
        return producerService.produceMessage(message);
    }
}
