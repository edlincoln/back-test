package com.example.backtest.service;

import com.example.backtest.entity.Accounts;
import com.example.backtest.exceptions.NotFoundException;
import com.example.backtest.repository.AccountsRepository;
import com.example.backtest.request.AccountRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountsRepository repository;

    @Override
    public Accounts listById(Long id) throws NotFoundException {
        log.debug("listing account id", id);
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Accounts save(AccountRequest request) {
        log.debug("saving account");
        var account = Accounts.builder()
                .documentNumber(request.getDocumentNumber())
                .build();

        return repository.save(account);
    }
}
