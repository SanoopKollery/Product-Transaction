package com.fse.cqrs.core.producers;

import com.fse.cqrs.core.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
