package com.itana.crud_csvbackend.resale.infrastructure.controllers;


import com.itana.crud_csvbackend.resale.domain.models.Transaction;
import com.itana.crud_csvbackend.resale.domain.services.TransactionService;
import com.itana.crud_csvbackend.resale.infrastructure.resources.request.TransactionRequest;
import com.itana.crud_csvbackend.resale.infrastructure.resources.response.TransactionResponse;
import com.itana.crud_csvbackend.shared.domain.exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/transactions", produces = "application/json")
@Tag(name = "Transactions", description = "The Transactions API")
public class TransactionController {
    private final TransactionService transactionService;
    private final ModelMapper modelMapper;
    public TransactionController(TransactionService transactionService, ModelMapper modelMapper) {
        this.transactionService = transactionService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @PostMapping("")
    public ResponseEntity<TransactionResponse> create(@RequestBody TransactionRequest transactionRequest) {
        var transactionId = transactionService.createTransaction(transactionRequest);
        if (transactionId == null) {
            throw new ValidationException("Transaction not created");
        }
        Optional<Transaction> transaction = transactionService.getById(transactionId);
        if (transaction.isEmpty()) {
            throw new ResourceNotFoundException("Transaction with id " + transactionId + " not found");
        }
        TransactionResponse transactionResponse = modelMapper.map(transaction, TransactionResponse.class);
        return new ResponseEntity<>(transactionResponse, HttpStatus.CREATED);
    }

    @Transactional(readOnly = true)
    @GetMapping("")
    public void getAll() {

    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public void getById(@PathVariable Long id) {

    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody TransactionRequest transactionRequest) {

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

    }
}
