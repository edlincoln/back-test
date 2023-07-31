package com.example.backtest.service;

import com.example.backtest.request.TransactionRequest;
import jakarta.validation.ValidationException;

public interface TransactionService {

    void save(TransactionRequest request) throws ValidationException;
}
