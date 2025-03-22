package net.example.banking_app.fakeSystem;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BankApiClient {
    public boolean transfer(String fromAccount, String toAccount, BigDecimal amount) {
        // Gửi request tới hệ thống trung gian (NAPAS, SWIFT)
        System.out.println("Gửi yêu cầu chuyển tiền từ " + fromAccount + " đến " + toAccount + " với số tiền " + amount);
        return true; // Giả lập thành công
    }
}
