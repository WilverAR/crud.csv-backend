package com.itana.crud_csvbackend.resale.infrastructure.repositories;

import com.itana.crud_csvbackend.resale.domain.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
