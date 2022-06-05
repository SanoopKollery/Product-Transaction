package com.fse.product.cmd.api.controllers;

import com.fse.cqrs.core.events.EventModel;
import com.fse.cqrs.core.infrastructure.CommandDispatcher;
import com.fse.product.cmd.api.commands.Transaction;
import com.fse.product.cmd.api.commands.UpdateBidAmountCommand;
import com.fse.product.cmd.api.dto.TransactionSaveResponse;
import com.fse.product.cmd.domain.EventStoreRepository;
import com.fse.product.common.dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/e-auction/api/v1/buyer")
public class UpdateBidController {

    private final Logger logger = Logger.getLogger(ProductTransactionController.class.getName());
    @Autowired
    private CommandDispatcher commandDispatcher;

    @Autowired
    private EventStoreRepository eventStoreRepository;

    @PutMapping("/update-bid/{productId}/{buyerEmailld}/{newBidAmount}")
    public ResponseEntity<BaseResponse> updateBidAmount(@PathVariable int productId,
                                                        @PathVariable String buyerEmailld,
                                                        @PathVariable BigDecimal newBidAmount) {
        UpdateBidAmountCommand command = new UpdateBidAmountCommand();
        EventModel eventModel = eventStoreRepository.findByProductIDAndEmail(productId,buyerEmailld);
        if (eventModel == null) {
            command.setTrx_ID(eventModel.getId( ));
            command.setId(eventModel.getId( ));
            command.setProductID(eventModel.getProductID( ));
            command.setEmailID(eventModel.getEmail( ));
            command.setNewBidAmount(newBidAmount);
        }
        try {
            commandDispatcher.send(command);
            return new ResponseEntity<>(new TransactionSaveResponse("Bid saved successfully!",command.getId()), HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        }
    }
}
