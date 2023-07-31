package com.example.backtest.service;

import com.example.backtest.entity.Transactions;
import com.example.backtest.repository.AccountsRepository;
import com.example.backtest.repository.OperationTypesRepository;
import com.example.backtest.repository.TransactionsRepository;
import com.example.backtest.request.TransactionRequest;
import com.example.backtest.use_case.operation.OperationsEnum;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private OperationTypesRepository operationTypesRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Override
    public void save(TransactionRequest request) throws ValidationException {
        log.debug("saving transaction");
        var account = accountsRepository.findById(request.getAccountId())
                .orElseThrow(() -> new ValidationException("account not found"));

        var operation = operationTypesRepository.findById(request.getOperationTypeId())
                .orElseThrow(() -> new ValidationException("operation type not found"));

        try {
            var operationTransaction = OperationsEnum.getOperatorClassByCode(request.getOperationTypeId().intValue());
            List<Transactions> transactions = operationTransaction.generateTransactions(account, operation, 1, request.getAmount());
            transactionsRepository.saveAll(transactions);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new ValidationException(e);
        }
    }
}
