package com.fse.product.cmd.infrastructure;

import com.fse.cqrs.core.domain.AggregateRoot;
import com.fse.cqrs.core.handlers.EventSourcingHandler;
import com.fse.cqrs.core.infrastructure.EventStore;
import com.fse.product.cmd.domain.ProductTrxnAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class ProductTrxnEventSourcingHandler implements EventSourcingHandler<ProductTrxnAggregate> {
    @Autowired
    private EventStore eventStore;


    @Override
    public void save(AggregateRoot aggregate) {

        eventStore.saveEvents(aggregate.getId(),aggregate.getProductID(),aggregate.getEmail(), aggregate.getUncommittedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommitted();
    }

    @Override
    public ProductTrxnAggregate getById(String id) {
        var aggregate = new ProductTrxnAggregate();
        var events = eventStore.getEvents(id);
        if (events != null && !events.isEmpty()) {
            aggregate.replayEvents(events);
            var latestVersion = events.stream().map(x -> x.getVersion()).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }
        return aggregate;
    }


}
