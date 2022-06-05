package com.fse.product.cmd.domain;

import com.fse.cqrs.core.domain.AggregateRoot;
import com.fse.product.cmd.api.commands.Transaction;
import com.fse.product.common.events.ProductTransactionEvent;

import com.fse.product.common.events.UpdateBidAmountCommand;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
public class ProductTrxnAggregate extends AggregateRoot {
    private BigDecimal bidAmount;

    public BigDecimal getBidAmount() {
        return this.bidAmount;
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
  public void apply(ProductTransactionEvent event) {
        this.id = event.getTrx_ID();
        this.productID = event.getProductId();
        this.email = event.getEmail();
    }

    public void updateBidAmount (String trxId, BigDecimal amount,int productID,
                                 String email) {

        raiseEvent(UpdateBidAmountCommand.builder()
                .id(trxId)
                .trx_ID(trxId)
                .productId(productID)
                .email(email)
                .bidAmount(amount)
                .build());
    }


}
