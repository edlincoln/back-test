package com.example.backtest.use_case.operation;

import com.example.backtest.entity.Accounts;
import com.example.backtest.entity.OperationTypes;
import com.example.backtest.entity.Transactions;
import jakarta.validation.ValidationException;

import java.math.BigDecimal;
import java.util.List;

public interface OperationTransaction {

    List<Transactions> generateTransactions(Accounts accounts, OperationTypes operationTypes, Integer size, BigDecimal amount) throws ValidationException;
}
