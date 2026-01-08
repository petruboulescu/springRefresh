package com.petruboulescu.service;

import com.petruboulescu.model.Transaction;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TransactionService {

    //Creates a thread safe empty list of transactions
    private List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public Transaction addTransaction(int amount, String reference) {
        Transaction transaction = new Transaction(amount, reference, ZonedDateTime.now());
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
