public class SavingsAccount {
    private static double annualInterestRate = 0.0;
    private double savingsBalance;

    public SavingsAccount(double balance) {
        if (balance < 0.0) {
            throw new IllegalArgumentException("Balance must be non-negative.");
        }
        this.savingsBalance = balance;
    }

    public void calculateMonthlyInterest() {
        double monthlyInterest = (savingsBalance * annualInterestRate) / 12.0;
        savingsBalance += monthlyInterest;
    }

    public static void modifyInterestRate(double newRate) {
        if (newRate < 0.0) {
            throw new IllegalArgumentException("Rate must be non-negative.");
        }
        annualInterestRate = newRate;
    }

    public double getSavingsBalance() {
        return savingsBalance;
    }
}
