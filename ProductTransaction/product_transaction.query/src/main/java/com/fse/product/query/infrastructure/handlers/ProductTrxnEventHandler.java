package com.fse.product.query.infrastructure.handlers;


import com.fse.product.common.events.ProductTransactionEvent;
import com.fse.product.query.domain.Transaction;
import com.fse.product.query.domain.ProductTrxnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTrxnEventHandler implements EventHandler {
    @Autowired
    private ProductTrxnRepository productTrxnRepository;


    @Override
    public void on(ProductTransactionEvent event) {
      //  ProductCategory cat = ProductCategory.valueOf(event.getCategory().toUpperCase());
        var transaction = Transaction.builder()
                .trx_ID(event.getTrx_ID())
                .firstName(event.getFirstName())
                .lastName(event.getLastName())
                .address(event.getAddress())
                .city(event.getCity())
                .state(event.getState())
                .pin(event.getPin())
                .phone(event.getPhone())
                .email(event.getEmail())
                .productId(event.getProductId())
                .bidAmount(event.getBidAmount())
                .build();
        productTrxnRepository.save(transaction);
    }
}
