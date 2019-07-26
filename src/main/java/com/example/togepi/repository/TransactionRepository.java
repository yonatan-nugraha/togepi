package com.example.togepi.repository;

import com.example.togepi.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface TransactionRepository  extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findById(Long transactionId);
}
