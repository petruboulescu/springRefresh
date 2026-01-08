package com.petruboulescu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.petruboulescu.service.TransactionService;

public class Application {

    public static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    public static final TransactionService transactionService = new TransactionService();
}
