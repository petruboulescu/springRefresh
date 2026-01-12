package com.petruboulescu.web;


import com.petruboulescu.dto.TransactionDto;
import com.petruboulescu.model.Transaction;
import com.petruboulescu.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Controller and ResponseBody
// https://github.com/spring-projects/spring-framework/blob/main/spring-web/src/main/java/org/springframework/web/bind/annotation/RestController.java
@RestController
public class BankingController {

    public TransactionService transactionService;

    public BankingController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }

    @PostMapping("/transactions")
    public Transaction addTransaction(@RequestBody @Valid  TransactionDto transaction) {
        return transactionService.addTransaction(transaction.getAmount(), transaction.getReference());
    }
}
