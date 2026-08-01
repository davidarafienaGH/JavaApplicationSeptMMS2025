package assignment1;


public class TestBanking {
    public static void main(String[] args){
        BankAccount account1 = new BankAccount("00234576890", "Jerry Smith", 350000.00);
        
        account1.displayAccountDetails();
        account1.deposit(265000.00);
        account1.withdraw(11500.50);
    }
}
