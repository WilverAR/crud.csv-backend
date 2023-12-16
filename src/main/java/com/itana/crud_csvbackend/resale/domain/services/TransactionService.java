package com.itana.crud_csvbackend.resale.domain.services;

import com.itana.crud_csvbackend.resale.domain.models.Transaction;
import com.itana.crud_csvbackend.resale.infrastructure.resources.request.TransactionRequest;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Long createTransaction(TransactionRequest transactionRequest);
    List<Transaction> getAllTransactions();
    Optional<Transaction> getTransaction(Long transactionId);
    Optional<Transaction> updateTransaction(Long transactionId, TransactionRequest transaction);
    void deleteTransaction(Long transactionId);
}
