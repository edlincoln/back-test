package com.example.backtest.resources;

import com.example.backtest.request.TransactionRequest;
import com.example.backtest.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "transactions")
public class TransactionsResource {
    @Autowired
    private TransactionService service;

    @PostMapping
    public ResponseEntity<?> createAccount(@Valid @RequestBody TransactionRequest request) {
        try {
            service.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
