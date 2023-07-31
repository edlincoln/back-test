package com.example.backtest.service;

import com.example.backtest.entity.Accounts;
import com.example.backtest.entity.OperationTypes;
import com.example.backtest.repository.AccountsRepository;
import com.example.backtest.repository.OperationTypesRepository;
import com.example.backtest.repository.TransactionsRepository;
import com.example.backtest.request.TransactionRequest;
import com.example.backtest.use_case.operation.OperationsEnum;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @Mock
    private AccountsRepository accountsRepository;

    @Mock
    private OperationTypesRepository operationTypesRepository;

    @Mock
    private TransactionsRepository transactionsRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private TransactionRequest validTransactionRequest;
    private Accounts mockAccount;
    private OperationTypes mockOperationType;

    @BeforeEach
    public void setUp() {
        validTransactionRequest = new TransactionRequest();
        validTransactionRequest.setAccountId(1L);
        validTransactionRequest.setOperationTypeId((long) OperationsEnum.COMPRA_A_VISTA.getCode());
        validTransactionRequest.setAmount(new BigDecimal("100.00"));

        mockAccount = new Accounts();
        mockAccount.setId(1L);

        mockOperationType = new OperationTypes();
        mockOperationType.setId((long) OperationsEnum.COMPRA_A_VISTA.getCode());
    }

    @Test
    public void testSaveTransaction() throws ValidationException {
        when(accountsRepository.findById(validTransactionRequest.getAccountId())).thenReturn(Optional.of(mockAccount));
        when(operationTypesRepository.findById(validTransactionRequest.getOperationTypeId())).thenReturn(Optional.of(mockOperationType));

        transactionService.save(validTransactionRequest);

        verify(accountsRepository, times(1)).findById(validTransactionRequest.getAccountId());
        verify(operationTypesRepository, times(1)).findById(validTransactionRequest.getOperationTypeId());
        verify(transactionsRepository, times(1)).saveAll(any());
    }

    @Test
    public void testSaveTransactionInvalidAccountId() {
        when(accountsRepository.findById(validTransactionRequest.getAccountId())).thenReturn(Optional.empty());

        assertThrows(ValidationException.class, () -> transactionService.save(validTransactionRequest));

        verify(accountsRepository, times(1)).findById(validTransactionRequest.getAccountId());
        verify(operationTypesRepository, never()).findById(anyLong());
        verify(transactionsRepository, never()).saveAll(any());
    }

    @Test
    public void testSaveTransactionInvalidOperationTypeId() {
        when(accountsRepository.findById(validTransactionRequest.getAccountId())).thenReturn(Optional.of(mockAccount));
        when(operationTypesRepository.findById(validTransactionRequest.getOperationTypeId())).thenReturn(Optional.empty());

        assertThrows(ValidationException.class, () -> transactionService.save(validTransactionRequest));

        verify(accountsRepository, times(1)).findById(validTransactionRequest.getAccountId());
        verify(operationTypesRepository, times(1)).findById(validTransactionRequest.getOperationTypeId());
        verify(transactionsRepository, never()).saveAll(any());
    }
}
