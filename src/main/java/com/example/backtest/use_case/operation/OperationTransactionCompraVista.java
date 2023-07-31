package com.example.backtest.use_case.operation;

import com.example.backtest.entity.Accounts;
import com.example.backtest.entity.OperationTypes;
import com.example.backtest.entity.Transactions;
import jakarta.validation.ValidationException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class OperationTransactionCompraVista implements OperationTransaction {

    private final Integer FACTOR = -1;

    @Override
    public List<Transactions> generateTransactions(Accounts accounts, OperationTypes operationTypes, Integer size, BigDecimal amount) throws ValidationException {
        if (size <= 0 || size > 1) {
            throw new ValidationException("Invalid transaction size");
        }

        Transactions transactions = Transactions.builder()
                .account(accounts)
                .operationType(operationTypes)
                .amount(amount.multiply(BigDecimal.valueOf(FACTOR)))
                .eventDate(LocalDateTime.now())
                .build();

        return Collections.singletonList(transactions);
    }
}
