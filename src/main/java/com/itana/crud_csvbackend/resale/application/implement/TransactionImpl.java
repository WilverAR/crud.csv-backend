package com.itana.crud_csvbackend.resale.application.implement;

import com.itana.crud_csvbackend.resale.domain.models.Transaction;
import com.itana.crud_csvbackend.resale.domain.services.TransactionService;
import com.itana.crud_csvbackend.resale.infrastructure.repositories.TransactionRepository;
import com.itana.crud_csvbackend.resale.infrastructure.resources.request.TransactionRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    public TransactionImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    @Override
    public Long createTransaction(TransactionRequest transactionRequest) {
        var transactionTest = new Transaction(transactionRequest.getMonth(), transactionRequest.getTown(), transactionRequest.getFlatType(),
                transactionRequest.getBlock(), transactionRequest.getStreetName(), transactionRequest.getStoreyRange(),
                transactionRequest.getFloorAreaSqm(), transactionRequest.getFlatModel(), transactionRequest.getLeaseCommenceDate(),
                transactionRequest.getResalePrice());
        transactionRepository.save(transactionTest);
        return transactionTest.getId();
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> getById(Long transactionId) {
        return transactionRepository.findById(transactionId);
    }

    @Override
    public Boolean updateTransaction(Long id, TransactionRequest transaction) {
        return transactionRepository.findById(id).map(transaction1 -> {
            transaction1.setMonth(transaction.getMonth());
            transaction1.setTown(transaction.getTown());
            transaction1.setFlatType(transaction.getFlatType());
            transaction1.setBlock(transaction.getBlock());
            transaction1.setStreetName(transaction.getStreetName());
            transaction1.setStoreyRange(transaction.getStoreyRange());
            transaction1.setFloorAreaSqm(transaction.getFloorAreaSqm());
            transaction1.setFlatModel(transaction.getFlatModel());
            transaction1.setLeaseCommenceDate(transaction.getLeaseCommenceDate());
            transaction1.setResalePrice(transaction.getResalePrice());
            transactionRepository.save(transaction1);
            return true;
        }).orElse(false);
    }

    @Override
    public Boolean deleteTransaction(Long id) {
        return transactionRepository.findById(id).map(transaction -> {
            transactionRepository.delete(transaction);
            return true;
        }).orElse(false);
    }
}
