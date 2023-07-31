package com.example.backtest.use_case.operation;

import com.example.backtest.entity.Accounts;
import com.example.backtest.entity.OperationTypes;
import com.example.backtest.entity.Transactions;
import jakarta.validation.ValidationException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OperationTransactionCompraParcelada implements OperationTransaction {

    private final Integer FACTOR = -1;

    @Override
    public List<Transactions> generateTransactions(Accounts accounts, OperationTypes operationTypes, Integer size, BigDecimal amount) throws ValidationException {
        if (size <= 0) {
            throw new ValidationException("Invalid transaction size");
        }

        List<Transactions> listTransactions = new ArrayList<>();
        BigDecimal parcelAmount = amount.divide(BigDecimal.valueOf(size));
        for (int i = 1; i <= size; i++) {

            listTransactions.add(Transactions.builder()
                    .account(accounts)
                    .operationType(operationTypes)
                    .amount(parcelAmount.multiply(BigDecimal.valueOf(FACTOR)))
                    .eventDate(LocalDateTime.now())
                    .build()
            );
        }
        return listTransactions;
    }
}
