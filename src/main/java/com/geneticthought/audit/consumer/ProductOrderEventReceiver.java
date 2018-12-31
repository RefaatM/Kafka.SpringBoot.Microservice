package com.geneticthought.audit.consumer;

import com.geneticthought.audit.service.ProductOrderEventProcessor ;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProductOrderEventReceiver {
    private ObjectMapper mapper = new ObjectMapper();

    public ProductOrderEventReceiver() {
        mapper.registerModule(new JavaTimeModule());
    }

    @Autowired
    private ProductOrderEventProcessor  processor;

    @KafkaListener(topics = "orders", groupId = "audit")
    public void receive(ConsumerRecord<?, ?> consumerRecord) throws IOException {
        ProductOrderEvent event = mapper.readValue(consumerRecord.value().toString(), ProductOrderEvent.class);
        processor.processEvent(event);

    }

}
