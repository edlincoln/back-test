package com.example.backtest.service;

import com.example.backtest.entity.Accounts;
import com.example.backtest.exceptions.NotFoundException;
import com.example.backtest.request.AccountRequest;

public interface AccountService {

    Accounts listById(Long id) throws NotFoundException;

    Accounts save(AccountRequest request);
}
