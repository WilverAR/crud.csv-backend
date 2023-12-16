package com.itana.crud_csvbackend.resale.application.implement;

import com.itana.crud_csvbackend.resale.domain.models.Transaction;
import com.itana.crud_csvbackend.resale.domain.services.TransactionService;
import com.itana.crud_csvbackend.resale.infrastructure.repositories.TransactionRepository;
import com.itana.crud_csvbackend.resale.infrastructure.resources.request.TransactionRequest;
import com.itana.crud_csvbackend.shared.domain.exceptions.ResourceNotFoundException;
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
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> getTransaction(Long transactionId) {
        return transactionRepository.findById(transactionId);
    }

    @Override
    public Optional<Transaction> updateTransaction(Long id, TransactionRequest transactionRequest) {
        if (!transactionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Transaction with id " + id + " not found");
        }
        Optional<Transaction> existingTransaction = transactionRepository.findById(id);
        if (existingTransaction.isPresent()) {
            Transaction transactionToUpdate = existingTransaction.get();
            transactionToUpdate.setMonth(transactionRequest.getMonth());
            transactionToUpdate.setTown(transactionRequest.getTown());
            transactionToUpdate.setFlatType(transactionRequest.getFlatType());
            transactionToUpdate.setBlock(transactionRequest.getBlock());
            transactionToUpdate.setStreetName(transactionRequest.getStreetName());
            transactionToUpdate.setStoreyRange(transactionRequest.getStoreyRange());
            transactionToUpdate.setFloorAreaSqm(transactionRequest.getFloorAreaSqm());
            transactionToUpdate.setFlatModel(transactionRequest.getFlatModel());
            transactionToUpdate.setLeaseCommenceDate(transactionRequest.getLeaseCommenceDate());
            transactionToUpdate.setResalePrice(transactionRequest.getResalePrice());
            transactionRepository.save(transactionToUpdate);
        }
        return existingTransaction;
    }

    @Override
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Transaction with id " + id + " not found");
        }
        transactionRepository.deleteById(id);
    }
}
