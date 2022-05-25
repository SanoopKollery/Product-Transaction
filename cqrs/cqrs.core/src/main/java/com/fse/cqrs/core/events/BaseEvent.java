package com.fse.cqrs.core.events;

import com.fse.cqrs.core.messages.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public  class BaseEvent extends Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int version;
}
