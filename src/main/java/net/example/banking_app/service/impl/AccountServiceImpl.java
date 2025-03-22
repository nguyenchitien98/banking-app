package net.example.banking_app.service.impl;

import net.example.banking_app.dto.request.AccountRequest;
import net.example.banking_app.dto.response.AccountResponse;
import net.example.banking_app.entity.Account;
import net.example.banking_app.exception.AccountException;
import net.example.banking_app.exception.InsufficientException;
import net.example.banking_app.mapper.AccountMapper;
import net.example.banking_app.repository.AccountRepository;
import net.example.banking_app.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountResponse createAccount(AccountRequest accountRequest) {
        Account account = AccountMapper.mapToAccount(accountRequest);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountResponse(savedAccount);
    }

    @Override
    public AccountResponse getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountException("Account not found"));
        return AccountMapper.mapToAccountResponse(account);
    }

    @Override
    public AccountResponse deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountException("Account not found"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountResponse(savedAccount);
    }

    @Override
    public AccountResponse withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountException("Account not found"));
        if (account.getBalance() - amount < 0) {
            throw new InsufficientException("Insufficient balance");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountResponse(savedAccount);
    }

    @Override
    public List<AccountResponse> getAllAccounts() {
//      List<Account> accounts = accountRepository.findAll();
//      accounts.stream().map(AccountMapper::mapToAccountResponse).collect(Collectors.toList());

      List<AccountResponse> accounts = accountRepository.findAll().stream().map(AccountMapper::mapToAccountResponse).collect(Collectors.toList());
      return accounts;
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountException("Account not found"));
        accountRepository.deleteById(id);
    }

}
