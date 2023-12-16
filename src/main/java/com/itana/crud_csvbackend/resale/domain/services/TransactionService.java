package com.itana.crud_csvbackend.resale.domain.services;

import com.itana.crud_csvbackend.resale.domain.models.Transaction;
import com.itana.crud_csvbackend.resale.infrastructure.resources.request.TransactionRequest;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    //CREATE
    Long createTransaction(TransactionRequest transactionRequest);
    //READ
    List<Transaction> getAll();
    Optional<Transaction> getById(Long transactionId);
    //UPDATE
    Boolean updateTransaction(Long id, TransactionRequest transaction);
    //DELETE
    Boolean deleteTransaction(Long id);
}
