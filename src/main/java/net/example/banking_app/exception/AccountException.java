package net.example.banking_app.exception;

public class AccountException extends RuntimeException {
    public AccountException(String message) {
        super(message);
    }
}
