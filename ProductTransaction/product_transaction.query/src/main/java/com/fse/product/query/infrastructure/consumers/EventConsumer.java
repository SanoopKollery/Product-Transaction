package com.fse.product.query.infrastructure.consumers;


import com.fse.product.common.events.ProductTransactionEvent;
import com.fse.product.common.events.UpdateBidAmountCommand;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload ProductTransactionEvent event, Acknowledgment ack);
    void consume(@Payload UpdateBidAmountCommand event, Acknowledgment ack);

}
