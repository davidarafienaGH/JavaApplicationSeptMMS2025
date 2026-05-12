// 2.26 Multiples 

import java.util.Scanner;

public class Multiples {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter first integer: ");
        int first = input.nextInt();

        System.out.print("Enter second integer: ");
        int second = input.nextInt();

        if (first % second == 0) {
            System.out.println(first + " is a multiple of " + second);
        } else {
            System.out.println(first + " is not a multiple of " + second);
        }

        input.close();
    }
}