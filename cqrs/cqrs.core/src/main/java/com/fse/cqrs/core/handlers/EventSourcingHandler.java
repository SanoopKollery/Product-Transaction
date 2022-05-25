package com.fse.cqrs.core.handlers;

import com.fse.cqrs.core.domain.AggregateRoot;


public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregate);

}
