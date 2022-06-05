package com.fse.product.common.events;

import com.fse.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UpdateBidAmountCommand extends BaseEvent {
    private String trx_ID;
    private String email;
    private int productId;
    private BigDecimal bidAmount;
}
