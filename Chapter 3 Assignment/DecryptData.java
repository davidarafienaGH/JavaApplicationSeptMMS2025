import java.util.Scanner;

public class DecryptData {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter encrypted four-digit integer: ");
        int number = input.nextInt();

        // Separate digits
        int digit1 = number / 1000;
        int digit2 = (number / 100) % 10;
        int digit3 = (number / 10) % 10;
        int digit4 = number % 10;

        // Reverse swapping
        int temp1 = digit1;
        int temp2 = digit2;

        digit1 = digit3;
        digit2 = digit4;
        digit3 = temp1;
        digit4 = temp2;

        // Decrypt digits
        digit1 = (digit1 + 10 - 7) % 10;
        digit2 = (digit2 + 10 - 7) % 10;
        digit3 = (digit3 + 10 - 7) % 10;
        digit4 = (digit4 + 10 - 7) % 10;

        // Form original number
        int originalNumber =
                digit1 * 1000 +
                digit2 * 100 +
                digit3 * 10 +
                digit4;

        System.out.println("Decrypted integer: " + originalNumber);

        input.close();
    }
}