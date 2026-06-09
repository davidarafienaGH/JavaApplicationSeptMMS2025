import java.util.Random;

public class PasswordGenerator{
    public static void main(String[] args) {
        Random random = new Random();

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                     + "abcdefghijklmnopqrstuvwxyz"
                     + "0123456789"
                     + "!@#$%^&*()-_=+[]{}<>?";

        String password = "";

        for (int i = 0; i < 15; i++) {
            password += chars.charAt(random.nextInt(chars.length()));
        }

        System.out.printf("Generated Password: %s%n", password);
    }
}