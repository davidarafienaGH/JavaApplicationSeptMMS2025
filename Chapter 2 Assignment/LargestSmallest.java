// 2.24 Largest and Smallest Integers

import java.util.Scanner;

public class LargestSmallest {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int number;
        int largest = Integer.MIN_VALUE;
        int smallest = Integer.MAX_VALUE;

        System.out.println("Enter five integers:");

        for (int i = 1; i <= 5; i++) {
            number = input.nextInt();

            if (number > largest) {
                largest = number;
            }

            if (number < smallest) {
                smallest = number;
            }
        }

        System.out.println("Largest integer: " + largest);
        System.out.println("Smallest integer: " + smallest);

        input.close();
    }
}