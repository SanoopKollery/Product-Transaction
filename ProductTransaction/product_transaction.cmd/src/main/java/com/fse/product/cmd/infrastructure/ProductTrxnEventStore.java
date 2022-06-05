package com.fse.product.cmd.infrastructure;

import com.fse.cqrs.core.events.BaseEvent;
import com.fse.cqrs.core.events.EventModel;
import com.fse.cqrs.core.infrastructure.EventStore;

import com.fse.cqrs.core.producers.EventProducer;
import com.fse.product.cmd.domain.EventStoreRepository;
import com.fse.product.cmd.domain.ProductTrxnAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductTrxnEventStore implements EventStore {
    @Autowired
    private EventProducer eventProducer;

    @Autowired
    private EventStoreRepository eventStoreRepository;


    @Override
    public void saveEvents(String aggregateId,int productID ,String email,Iterable<BaseEvent> events, int expectedVersion) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        //Check its alredy saved or not
        if (expectedVersion == -1 && eventStoreRepository.findByProductIDAndEmail(productID,email) != null)
            throw new IllegalStateException("Already Saved this record !!");
        if (expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion) {
            throw new RuntimeException("expected version is -1");
        }
        var version = expectedVersion;
        for (var event: events) {
          //  aggregateId = UUID.randomUUID().toString();
            version++;
            event.setVersion(version);
            var eventModel = EventModel.builder()
                    .timeStamp(new Date())
                    .id(aggregateId) //sanoop
                    .productID(productID <=0 ? eventStream.get(eventStream.size() - 1).getProductID() : productID)
                    .email(email== null ? eventStream.get(eventStream.size() - 1).getEmail() : email)
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(ProductTrxnAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .eventData(event)
                    .build();
            var persistedEvent = eventStoreRepository.save(eventModel);
            if (persistedEvent.getId()!=null) {
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (eventStream == null || eventStream.isEmpty()) {
           // throw new AggregateNotFoundException("Incorrect account ID provided!");
        }
        //sanoop
      eventStream.forEach(v-> v.setEventData(new BaseEvent(v.getVersion())));
        return eventStream.stream().map(x -> x.getEventData()).collect(Collectors.toList());

    }
}
