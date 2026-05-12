// 2.16 Comparing Integers
import java.util.Scanner;

public class CompareIntegers {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int num1;
        int num2;

        System.out.print("Enter first integer: ");
        num1 = input.nextInt();

        System.out.print("Enter second integer: ");
        num2 = input.nextInt();

        if (num1 > num2)
            System.out.println(num1 + " is larger");

        if (num2 > num1)
            System.out.println(num2 + " is larger");

        if (num1 == num2)
            System.out.println("These numbers are equal");
    }
}