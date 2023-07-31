package com.example.backtest.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {

    @NotNull(message = "AccountId is required")
    private Long AccountId;

    @NotNull(message = "OperationTypeId is required")
    private Long OperationTypeId;

    @Min(value = 0, message = "Invalid value")
    private BigDecimal amount;
}
