package net.example.banking_app.controller;

import net.example.banking_app.dto.request.TransferRequest;
import net.example.banking_app.entity.Transaction;
import net.example.banking_app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transferMoney(@RequestBody TransferRequest request) {
        Transaction transaction = transactionService.processTransaction(
                request.getFromAccount(),
                request.getToAccount(),
                request.getAmount()
        );
        return ResponseEntity.ok(transaction);
    }
}
