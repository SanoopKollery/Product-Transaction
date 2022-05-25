package com.fse.product.cmd.api.controllers;


import com.fse.cqrs.core.infrastructure.CommandDispatcher;
import com.fse.product.cmd.api.commands.Transaction;
import com.fse.product.cmd.api.dto.TransactionSaveResponse;
import com.fse.product.common.dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/e-auction/api/v1/buyer")
public class ProductTransactionController {
    private final Logger logger = Logger.getLogger(ProductTransactionController.class.getName());
    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping("/place-bid")
    public ResponseEntity<BaseResponse> saveTransaction(@RequestBody Transaction command) {
            String id  = UUID.randomUUID().toString();
            command.setTrx_ID(id);
        try {
            commandDispatcher.send(command);
            return new ResponseEntity<>(new TransactionSaveResponse("Bid saved successfully!",id), HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        }
    }

}

