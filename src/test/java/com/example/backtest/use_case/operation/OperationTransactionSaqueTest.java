package com.example.backtest.use_case.operation;

import com.example.backtest.entity.Accounts;
import com.example.backtest.entity.OperationTypes;
import com.example.backtest.entity.Transactions;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class OperationTransactionSaqueTest {

    private OperationTransactionSaque operationTransactionSaque;
    private Accounts mockAccounts;
    private OperationTypes mockOperationTypes;

    @BeforeEach
    public void setUp() {
        operationTransactionSaque = new OperationTransactionSaque();
        mockAccounts = mock(Accounts.class);
        mockOperationTypes = mock(OperationTypes.class);
    }

    @Test
    public void testGenerateTransactionsValid() throws ValidationException {
        BigDecimal amount = new BigDecimal("100");
        int size = 1;

        List<Transactions> transactionsList = operationTransactionSaque.generateTransactions(mockAccounts, mockOperationTypes, size, amount);

        assertNotNull(transactionsList);
        assertEquals(1, transactionsList.size());

        Transactions transaction = transactionsList.get(0);
        assertSame(mockAccounts, transaction.getAccount());
        assertSame(mockOperationTypes, transaction.getOperationType());
        assertEquals(amount.negate(), transaction.getAmount());
        assertNotNull(transaction.getEventDate());
    }

    @Test
    public void testGenerateTransactionsInvalidSize() {
        BigDecimal amount = new BigDecimal("100");
        int invalidSize = 0;

        assertThrows(ValidationException.class, () -> operationTransactionSaque.generateTransactions(mockAccounts, mockOperationTypes, invalidSize, amount));
    }
}
