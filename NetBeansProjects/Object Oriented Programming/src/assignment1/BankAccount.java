
package assignment1;

public class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    
    public BankAccount(String accountNumber, String accountHolder, double balance){
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    public void deposit(double amount){
        if(amount >= 0){
            balance += amount;
            System.out.println("Your new balance is " + balance);
        }
    }
    public void withdraw(double amount){
        if(amount > 0 && amount <= balance){
            balance = balance - amount;
            System.out.println("Withdrew: " + amount);
            System.out.println("New balance is " + balance);
        }
    }
    public void displayAccountDetails(){
        System.out.println("Your account name: " + accountHolder);
        System.out.println("Your account balance: " + balance);
        System.out.println("Your account number: " + accountNumber);
    }
}
