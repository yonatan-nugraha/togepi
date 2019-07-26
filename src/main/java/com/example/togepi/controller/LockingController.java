package com.example.togepi.controller;

import com.example.togepi.entity.Transaction;
import com.example.togepi.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@Slf4j
public class LockingController {

    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping(value = "/transactions")
    public ResponseEntity<String> createTransaction(@Valid @RequestBody Transaction transaction) throws Exception {
        transactionRepository.save(transaction);
        return ResponseEntity.ok("Create transaction success!!");
    }

    @PutMapping(value = "/transactions/{transactionId}")
    public ResponseEntity<String> updateTransaction(@PathVariable Long transactionId) throws Exception {
        final ExecutorService pool = Executors.newFixedThreadPool(2);

        final List<CompletableFuture> completableFutures = new ArrayList<>();

       final CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
           final Transaction transaction = new Transaction();
           transaction.setAmount(BigDecimal.TEN);
           transaction.setStatus("SUCCESS");
           transactionRepository.save(transaction);
        }, pool);

        completableFutures.add(completableFuture);

        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()])).join();

        return ResponseEntity.ok("Update transaction success!!");
    }
}
