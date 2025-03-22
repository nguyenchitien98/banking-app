package net.example.banking_app.service.impl;

import net.example.banking_app.entity.Transaction;
import net.example.banking_app.fakeSystem.BankApiClient;
import net.example.banking_app.repository.TransactionRepository;
import net.example.banking_app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private BankApiClient bankApiClient;

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction processTransaction(String fromAccount, String toAccount, BigDecimal amount) {
        // 1. Tao giao dich moi
        Transaction transaction = new Transaction();
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setAmount(amount);
        transaction.setStatus("PENDING");

        transaction = transactionRepository.save(transaction);

        // 2. Goi API toi he thong trung gian
        boolean isSuccess = bankApiClient.transfer(fromAccount, toAccount, amount);

        // 3. Cap nhat trang thai giao dich
        if (isSuccess) {
            transaction.setStatus("SUCCESS");
        }else {
            transaction.setStatus("FAILED");
        }

        return transactionRepository.save(transaction);
    }
}
