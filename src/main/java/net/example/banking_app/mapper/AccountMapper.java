package net.example.banking_app.mapper;

import net.example.banking_app.dto.request.AccountRequest;
import net.example.banking_app.dto.response.AccountResponse;
import net.example.banking_app.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountRequest accountRequest) {
        Account account = new Account(
                accountRequest.getId(),
                accountRequest.getAccountHolderName(),
                accountRequest.getBalance()
        );
        return account;
    };

    public static AccountResponse mapToAccountResponse(Account account) {
        AccountResponse accountResponse = new AccountResponse(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountResponse;
    }
}
