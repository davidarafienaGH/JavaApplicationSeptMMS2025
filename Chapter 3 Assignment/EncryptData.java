import java.util.Scanner;

public class EncryptData {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter a four-digit integer: ");
        int number = input.nextInt();

        // Separate digits
        int digit1 = number / 1000;
        int digit2 = (number / 100) % 10;
        int digit3 = (number / 10) % 10;
        int digit4 = number % 10;

        // Encrypt digits
        digit1 = (digit1 + 7) % 10;
        digit2 = (digit2 + 7) % 10;
        digit3 = (digit3 + 7) % 10;
        digit4 = (digit4 + 7) % 10;

        // Swap digits
        int encryptedNumber =
                digit3 * 1000 +
                digit4 * 100 +
                digit1 * 10 +
                digit2;

        System.out.println("Encrypted integer: " + encryptedNumber);

        input.close();
    }
}