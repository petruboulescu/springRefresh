package com.petruboulescu.service;

import com.petruboulescu.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TransactionService {

    //Creates a thread safe empty list of transactions
    private List<Transaction> transactions = new CopyOnWriteArrayList<>();
    private final String slogan;

    public TransactionService(@Value ("${bank.slogan}") String slogan) {
        this.slogan = slogan;
    }

    public Transaction addTransaction(int amount, String reference) {
        Transaction transaction = new Transaction(amount, reference, ZonedDateTime.now(), slogan);
        transactions.add(transaction);
        return transaction;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
