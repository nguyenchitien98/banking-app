package net.example.banking_app.service;

import jakarta.transaction.Transactional;
import net.example.banking_app.entity.Transaction;

import java.math.BigDecimal;

public interface TransactionService {

    @Transactional
    public Transaction processTransaction(String fromAccount, String toAccount, BigDecimal amount);
}
