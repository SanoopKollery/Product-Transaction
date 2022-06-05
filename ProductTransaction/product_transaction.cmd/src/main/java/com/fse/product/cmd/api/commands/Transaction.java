package com.fse.product.cmd.api.commands;

import com.fse.cqrs.core.commands.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseCommand {

    private String trx_ID;
    @NotNull(message="First name should be mandatory!")
    @Size(min=5,max=30,message=" First name length should be between 5 and 30 !")
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String pin;
    private String phone;
    private String email;
    private int productId;
    private BigDecimal bidAmount;
}
