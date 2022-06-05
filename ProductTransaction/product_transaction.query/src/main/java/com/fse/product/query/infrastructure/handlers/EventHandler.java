package com.fse.product.query.infrastructure.handlers;

import com.fse.product.common.events.ProductTransactionEvent;
import com.fse.product.common.events.UpdateBidAmountCommand;


public interface EventHandler {
    void on(ProductTransactionEvent event);
    void on(UpdateBidAmountCommand event);

}
