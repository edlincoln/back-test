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

public class OperationTransactionPagamentoTest {

    private OperationTransactionPagamento operationTransactionPagamento;
    private Accounts mockAccounts;
    private OperationTypes mockOperationTypes;

    @BeforeEach
    public void setUp() {
        operationTransactionPagamento = new OperationTransactionPagamento();
        mockAccounts = mock(Accounts.class);
        mockOperationTypes = mock(OperationTypes.class);
    }

    @Test
    public void testGenerateTransactionsValid() throws ValidationException {
        BigDecimal amount = new BigDecimal("800");
        int size = 1;

        List<Transactions> transactionsList = operationTransactionPagamento.generateTransactions(mockAccounts, mockOperationTypes, size, amount);

        assertNotNull(transactionsList);
        assertEquals(1, transactionsList.size());

        Transactions transaction = transactionsList.get(0);
        assertSame(mockAccounts, transaction.getAccount());
        assertSame(mockOperationTypes, transaction.getOperationType());
        assertEquals(amount, transaction.getAmount());
        assertNotNull(transaction.getEventDate());
    }

    @Test
    public void testGenerateTransactionsInvalidSize() {
        BigDecimal amount = new BigDecimal("800");
        int invalidSize = 0;

        assertThrows(ValidationException.class, () -> operationTransactionPagamento.generateTransactions(mockAccounts, mockOperationTypes, invalidSize, amount));
    }

    @Test
    public void testGenerateTransactionsSizeGreaterThanOne() {
        BigDecimal amount = new BigDecimal("800");
        int invalidSize = 2;

        assertThrows(ValidationException.class, () -> operationTransactionPagamento.generateTransactions(mockAccounts, mockOperationTypes, invalidSize, amount));
    }
}
