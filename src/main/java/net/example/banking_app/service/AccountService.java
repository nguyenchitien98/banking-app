package net.example.banking_app.service;

import net.example.banking_app.dto.request.AccountRequest;
import net.example.banking_app.dto.response.AccountResponse;

import java.util.List;

public interface AccountService {
    AccountResponse createAccount(AccountRequest account);

    AccountResponse getAccountById(Long id);

    AccountResponse deposit(Long id, double amount);

    AccountResponse withdraw(Long id, double amount);

    List<AccountResponse> getAllAccounts();

    void deleteAccount(Long id);
}
