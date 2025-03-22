package net.example.banking_app.dto.request;

import lombok.Data;

@Data
public class AccountRequest {
    private Long id;
    private String accountHolderName;
    private double balance;
}
