package com.petruboulescu.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petruboulescu.context.ApplicationConfiguration;
import com.petruboulescu.model.Transaction;
import com.petruboulescu.service.TransactionService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;

public class BankingServlet extends HttpServlet {

    private TransactionService transactionService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        transactionService = applicationContext.getBean(TransactionService.class);
        objectMapper = applicationContext.getBean(ObjectMapper.class);

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        if (request.getRequestURI().equalsIgnoreCase("/transactions")) {
            List<Transaction> transactions = transactionService.getTransactions();
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print(objectMapper.writeValueAsString(transactions));
        }
        else response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equalsIgnoreCase("/transactions")) {
            int amount = Integer.parseInt(request.getParameter("amount"));
            String reference = request.getParameter("reference");
            Transaction transaction = transactionService.addTransaction(amount, reference);
            response.setContentType("application/json; charset=UTF-8");
            String json = objectMapper.writeValueAsString(transaction);
            response.getWriter().print(json);
        }
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        response.setContentType("application/json");

    }
}
