package com.fse.product.cmd.api.dto;

import com.fse.product.common.dto.BaseResponse;

import lombok.Data;

@Data
public class TransactionSaveResponse extends BaseResponse {
    private String id;

    public TransactionSaveResponse(String message, String id) {
        super(message);
        this.id = id;
    }
}
