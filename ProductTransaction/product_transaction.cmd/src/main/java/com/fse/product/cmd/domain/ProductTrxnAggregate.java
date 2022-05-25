package com.fse.product.cmd.domain;

import com.fse.cqrs.core.domain.AggregateRoot;
import com.fse.product.cmd.api.commands.Transaction;
import com.fse.product.common.events.ProductTransactionEvent;

import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
public class ProductTrxnAggregate extends AggregateRoot {
    private Boolean active;
    private double balance;

    public double getBalance() {
        return this.balance;
    }

    public ProductTrxnAggregate(Transaction command) {

        boolean futureOrNot = true;//getFutureOrNot(command.getBidEndDate());
        if(futureOrNot) {
            raiseEvent(ProductTransactionEvent.builder()
                    .trx_ID(command.getTrx_ID())
                    .firstName(command.getFirstName())
                    .lastName(command.getLastName())
                    .address(command.getAddress())
                    .city(command.getCity())
                    .state(command.getState())
                    .pin(command.getPin())
                    .phone(command.getPhone())
                    .email(command.getEmail())
                    .productId(command.getProductId())
                    .bidAmount(command.getBidAmount())
                    .build());
        }
    }

    private static boolean getFutureOrNot(Timestamp ts)
    {
        if(ts.after(new Timestamp(System.currentTimeMillis())))
            return true;
        else
            return false;
    }
  /* public void apply(ProductTransactionEvent event) {
        this.id = event.getId();
        this.active = true;
       // this. = event.getCategory();
    }*/


}
