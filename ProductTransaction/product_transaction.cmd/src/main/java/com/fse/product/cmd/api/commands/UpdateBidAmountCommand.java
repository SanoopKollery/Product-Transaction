package com.fse.product.cmd.api.commands;

import com.fse.cqrs.core.commands.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBidAmountCommand extends BaseCommand {
    private String trx_ID;
    private BigDecimal newBidAmount;
    private int productID;
    private String emailID;
}
