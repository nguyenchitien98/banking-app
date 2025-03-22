package net.example.banking_app.exception;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime date, String message, String details, String errorCode) {

}
