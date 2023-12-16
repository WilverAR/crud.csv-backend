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

import java.util.List;
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
        Optional<Transaction> transaction = transactionService.getTransaction(transactionId);
        if (transaction.isEmpty()) {
            throw new ResourceNotFoundException("Transaction with id " + transactionId + " not found");
        }
        TransactionResponse transactionResponse = modelMapper.map(transaction, TransactionResponse.class);
        return new ResponseEntity<>(transactionResponse, HttpStatus.CREATED);
    }

    @Transactional(readOnly = true)
    @GetMapping("")
    public ResponseEntity<List<TransactionResponse>> getAll() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        List<TransactionResponse> transactionResponses = transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionResponse.class))
                .toList();
        return new ResponseEntity<>(transactionResponses, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> get(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.getTransaction(id);
        if (transaction.isEmpty()) {
            throw new ResourceNotFoundException("Transaction with id " + id + " not found");
        }
        TransactionResponse transactionResponse = modelMapper.map(transaction, TransactionResponse.class);
        return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> update(@PathVariable Long id, @RequestBody TransactionRequest transactionRequest) {
        var updatedTransaction = transactionService.updateTransaction(id, transactionRequest);
        if (updatedTransaction.isEmpty()) {
            throw new ResourceNotFoundException("Transaction with id " + id + " not found");
        }
        TransactionResponse transactionResponse = modelMapper.map(updatedTransaction, TransactionResponse.class);
        return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>("Transaction with given id successfully deleted.", HttpStatus.OK);
    }
}
