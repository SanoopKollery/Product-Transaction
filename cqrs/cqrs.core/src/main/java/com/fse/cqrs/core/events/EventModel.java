package com.fse.cqrs.core.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EventModel {

    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private int productID;
    private String email;
    private Date timeStamp;
    private String aggregateIdentifier;
    private String aggregateType;
    private int version;
    private String eventType;
    @OneToOne(cascade = CascadeType.ALL)
    private BaseEvent eventData;
}
