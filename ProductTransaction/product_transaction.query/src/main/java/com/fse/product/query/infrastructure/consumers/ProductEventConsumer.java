package com.fse.product.query.infrastructure.consumers;


import com.fse.product.common.events.ProductTransactionEvent;
import com.fse.product.query.infrastructure.handlers.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ProductEventConsumer implements EventConsumer {
    @Autowired
    private EventHandler eventHandler;

    @KafkaListener(topics = "ProductTransactionEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload ProductTransactionEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }


}
