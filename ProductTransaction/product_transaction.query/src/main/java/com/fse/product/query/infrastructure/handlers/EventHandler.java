package com.fse.product.query.infrastructure.handlers;

import com.fse.product.common.events.ProductTransactionEvent;


public interface EventHandler {
    void on(ProductTransactionEvent event);

}
