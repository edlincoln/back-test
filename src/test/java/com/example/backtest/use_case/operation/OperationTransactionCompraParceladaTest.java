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

public class OperationTransactionCompraParceladaTest {

    private OperationTransactionCompraParcelada operationTransactionCompraParcelada;
    private Accounts mockAccounts;
    private OperationTypes mockOperationTypes;

    @BeforeEach
    public void setUp() {
        operationTransactionCompraParcelada = new OperationTransactionCompraParcelada();
        mockAccounts = mock(Accounts.class);
        mockOperationTypes = mock(OperationTypes.class);
    }

    @Test
    public void testGenerateTransactionsValid() throws ValidationException {
        BigDecimal amount = new BigDecimal("300");
        int size = 3;

        List<Transactions> transactionsList = operationTransactionCompraParcelada.generateTransactions(mockAccounts, mockOperationTypes, size, amount);

        assertNotNull(transactionsList);
        assertEquals(size, transactionsList.size());

        BigDecimal parcelAmount = amount.divide(BigDecimal.valueOf(size));

        for (Transactions transaction : transactionsList) {
            assertSame(mockAccounts, transaction.getAccount());
            assertSame(mockOperationTypes, transaction.getOperationType());
            assertEquals(parcelAmount.negate(), transaction.getAmount());
            assertNotNull(transaction.getEventDate());
        }
    }

    @Test
    public void testGenerateTransactionsInvalidSize() {
        BigDecimal amount = new BigDecimal("300");
        int invalidSize = 0;

        assertThrows(ValidationException.class, () -> operationTransactionCompraParcelada.generateTransactions(mockAccounts, mockOperationTypes, invalidSize, amount));
    }
}
