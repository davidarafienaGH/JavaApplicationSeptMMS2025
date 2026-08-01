import java.math.BigDecimal;
import java.math.RoundingMode;

public class Account {
    private String name;
    private BigDecimal balance;

    public Account(String name, double balance) {
        this.name = name;
        if (balance < 0.0) {
            throw new IllegalArgumentException("Initial balance must be >= 0");
        }
        this.balance = BigDecimal.valueOf(balance).setScale(2, RoundingMode.HALF_UP);
    }

    public void deposit(double depositAmount) {
        if (depositAmount <= 0.0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        BigDecimal amount = BigDecimal.valueOf(depositAmount);
        balance = balance.add(amount).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }
}
