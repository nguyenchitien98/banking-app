package net.example.banking_app.controller;

import net.example.banking_app.dto.request.AccountRequest;
import net.example.banking_app.dto.response.AccountResponse;
import net.example.banking_app.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Add account Rest API
    @PostMapping
    public ResponseEntity<AccountResponse> saveAccount(@RequestBody AccountRequest accountRequest) {
        return new ResponseEntity<>(accountService.createAccount(accountRequest), HttpStatus.CREATED);
    }

    // Get User by ID
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable("id") Long id) {
            AccountResponse accountResponse = accountService.getAccountById(id);
            return ResponseEntity.ok(accountResponse);

//        return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
    }

    // Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountResponse> deposit(@PathVariable("id") Long id,@RequestBody Map<String, Double> request) {

        Double amount = request.get("amount");
        AccountResponse accountResponse = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountResponse);
    }

    // withdraw Rest API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountResponse> withdraw(@PathVariable("id") Long id,@RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountResponse accountResponse = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountResponse);
    }

    // Get All Account Rest API
    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        List<AccountResponse> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    // Delete Account Rest API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
