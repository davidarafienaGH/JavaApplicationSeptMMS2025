
package abstractassignment;

public abstract class BankAccount {
    public String accountNumber;
    public String accountHolder;
    public double balance;
    
    public BankAccount(String accountNumber, String accountHolder, double balance){
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }
    void deposit(double amount){
        if(amount > 0){
            balance += amount;
            System.out.println("Deposit: " + amount);
            System.out.println("New Balance: "+ balance);
        }
        else{
            System.out.println("Invalid deposit");
        }
    }
    void displayBalance(){
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Name: " + accountHolder);
        System.out.println("Account Balance: " + balance);
    }
    public abstract void withdraw(double amount);
    public abstract double calculateInterest();
    
}
