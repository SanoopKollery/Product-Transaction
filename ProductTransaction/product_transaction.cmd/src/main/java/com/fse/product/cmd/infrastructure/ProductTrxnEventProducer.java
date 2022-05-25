package com.fse.product.cmd.infrastructure;

import com.fse.cqrs.core.events.BaseEvent;
import com.fse.cqrs.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductTrxnEventProducer implements EventProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void produce(String topic, BaseEvent event)
    {
        this.kafkaTemplate.send(topic, event);
    }
}
