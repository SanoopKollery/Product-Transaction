package com.fse.cqrs.core.infrastructure;

import com.fse.cqrs.core.events.BaseEvent;

import java.util.List;

public interface EventStore {
    void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion);

}
