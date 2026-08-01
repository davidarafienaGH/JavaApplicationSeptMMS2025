public class AccountTest {
    public static void main(String[] args) {
        Account acc = new Account("Jane Green", 50.00);
        System.out.printf("%s balance: $%.2f%n", acc.getName(), acc.getBalance());

        acc.deposit(25.53);
        System.out.printf("After deposit of $25.53, %s balance: $%.2f%n", acc.getName(), acc.getBalance());
    }
}
