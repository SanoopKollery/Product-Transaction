package com.fse.product.cmd.domain;


import com.fse.cqrs.core.events.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventStoreRepository extends JpaRepository<EventModel, Long> {

    List<EventModel> findByAggregateIdentifier(String aggregateIdentifier);
}
