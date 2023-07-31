package com.example.backtest.service;


import com.example.backtest.entity.Accounts;
import com.example.backtest.exceptions.NotFoundException;
import com.example.backtest.repository.AccountsRepository;
import com.example.backtest.request.AccountRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @Mock
    private AccountsRepository accountsRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private AccountRequest validAccountRequest;

    @BeforeEach
    public void setUp() {
        validAccountRequest = new AccountRequest();
        validAccountRequest.setDocumentNumber("123456789");
    }

    @Test
    public void testSaveAccount() {
        Accounts savedAccount = new Accounts();
        savedAccount.setId(1L);
        savedAccount.setDocumentNumber(validAccountRequest.getDocumentNumber());

        when(accountsRepository.save(any())).thenReturn(savedAccount);

        Accounts createdAccount = accountService.save(validAccountRequest);

        assertNotNull(createdAccount);
        assertEquals(savedAccount.getId(), createdAccount.getId());
        assertEquals(savedAccount.getDocumentNumber(), createdAccount.getDocumentNumber());

        verify(accountsRepository, times(1)).save(any());
    }

    @Test
    public void testListByIdValid() throws NotFoundException {
        Long accountId = 1L;
        Accounts existingAccount = new Accounts();
        existingAccount.setId(accountId);
        existingAccount.setDocumentNumber(validAccountRequest.getDocumentNumber());

        when(accountsRepository.findById(accountId)).thenReturn(Optional.of(existingAccount));

        Accounts retrievedAccount = accountService.listById(accountId);

        assertNotNull(retrievedAccount);
        assertEquals(existingAccount.getId(), retrievedAccount.getId());
        assertEquals(existingAccount.getDocumentNumber(), retrievedAccount.getDocumentNumber());

        verify(accountsRepository, times(1)).findById(accountId);
    }

    @Test
    public void testListByIdNotFound() {
        Long nonExistentAccountId = 100L;

        when(accountsRepository.findById(nonExistentAccountId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> accountService.listById(nonExistentAccountId));

        verify(accountsRepository, times(1)).findById(nonExistentAccountId);
    }
}
