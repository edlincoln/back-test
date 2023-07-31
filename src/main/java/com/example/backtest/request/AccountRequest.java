package com.example.backtest.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountRequest {
    @NotNull(message = "Document number is required")
    private String documentNumber;
}
