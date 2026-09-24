
package abstractassignment;

public class SavingsAccount extends BankAccount{
    public SavingsAccount(String accountNumber, String accountHolder, double balance){
        super(accountNumber, accountHolder, balance);
    }
    
    @Override
    public void withdraw(double amount){
        if(amount > 0 && amount <= balance){
            balance -= amount;
            System.out.println("Withdrawal: " + amount);
            System.out.println("New Balance: " + balance);
        }
        else{
            System.out.println("Insufficient balance");
        }
    }
    @Override
    public double calculateInterest(){
        double interest = 0.15;
        return balance * interest;
    }
}
