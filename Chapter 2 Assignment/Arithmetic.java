// 2.15 Arithmetic Application
import java.util.Scanner;

public class Arithmetic {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int num1;
        int num2;

        System.out.print("Enter first integer: ");
        num1 = input.nextInt();

        System.out.print("Enter second integer: ");
        num2 = input.nextInt();

        System.out.println("Sum = " + (num1 + num2));
        System.out.println("Product = " + (num1 * num2));
        System.out.println("Difference = " + (num1 - num2));
        System.out.println("Quotient = " + (num1 / num2));
    }
}