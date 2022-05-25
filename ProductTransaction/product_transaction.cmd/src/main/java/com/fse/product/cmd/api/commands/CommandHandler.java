package com.fse.product.cmd.api.commands;

import com.fse.product.common.events.ProductTransactionEvent;

public interface CommandHandler {
    void handle(Transaction command);

}
