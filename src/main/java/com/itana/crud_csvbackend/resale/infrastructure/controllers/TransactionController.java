package com.itana.crud_csvbackend.resale.infrastructure.controllers;


import com.itana.crud_csvbackend.resale.domain.models.Transaction;
import com.itana.crud_csvbackend.resale.domain.services.TransactionService;
import com.itana.crud_csvbackend.resale.infrastructure.resources.request.TransactionRequest;
import com.itana.crud_csvbackend.resale.infrastructure.resources.response.TransactionResponse;
import com.itana.crud_csvbackend.shared.domain.exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@CrossOrigin(origins = "*")
@Tag(name = "Transactions", description = "The Transactions API")
public class TransactionController {
    private final TransactionService transactionService;
    private final ModelMapper modelMapper;
    public TransactionController(TransactionService transactionService, ModelMapper modelMapper) {
        this.transactionService = transactionService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<TransactionResponse> create(@Valid @RequestBody TransactionRequest transactionRequest) {
        var transactionId = transactionService.createTransaction(transactionRequest);
        if (transactionId == null) {
            throw new ValidationException("Transaction not created");
        }
        Optional<Transaction> transaction = transactionService.getTransaction(transactionId);
        TransactionResponse transactionResponse = transaction.map(t -> modelMapper.map(t, TransactionResponse.class))
                .orElseThrow(() -> new ResourceNotFoundException("Transaction with id " + transactionId + " not found"));
        System.out.println(transactionResponse);
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
        TransactionResponse transactionResponse = transaction.map(t -> modelMapper.map(t, TransactionResponse.class))
                .orElseThrow(() -> new ResourceNotFoundException("Transaction with id " + id + " not found"));
        return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> update(@PathVariable Long id, @RequestBody TransactionRequest transactionRequest) {
        Optional<Transaction> updatedTransaction = transactionService.updateTransaction(id, transactionRequest);
        TransactionResponse transactionResponse = updatedTransaction.map(t -> modelMapper.map(t, TransactionResponse.class))
                .orElseThrow(() -> new ResourceNotFoundException("Transaction with id " + id + " not found"));
        return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}