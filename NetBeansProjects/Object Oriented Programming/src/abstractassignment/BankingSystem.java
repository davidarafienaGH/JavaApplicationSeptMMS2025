
package abstractassignment;

public class BankingSystem {
    public static void main(String[] args){
        SavingsAccount savings = new SavingsAccount("20523789910", "Monica Rambeu", 500000.00);
        CurrentAccount current = new CurrentAccount("20523245680", "Delilah Rambeu", 250000.00);
        
        savings.deposit(100000.00);
        savings.withdraw(50000.00);
        savings.displayBalance();
        double savingsInterest = savings.calculateInterest();
        System.out.println("Savings Interest: " + savingsInterest);
        System.out.println("============================================");
        current.deposit(1000000.00);
        current.withdraw(110000.00);
        current.displayBalance();
        double currentInterest = current.calculateInterest();
        System.out.println("Current Interest: " + currentInterest);
    }
}
