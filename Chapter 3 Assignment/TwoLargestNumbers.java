import java.util.Scanner;

public class TwoLargestNumbers {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        int counter = 1;

        while (counter <= 10) {

            System.out.print("Enter integer " + counter + ": ");
            int number = input.nextInt();

            if (number > largest) {

                secondLargest = largest;
                largest = number;

            } else if (number > secondLargest) {

                secondLargest = number;
            }

            counter++;
        }

        System.out.println("\nLargest number: " + largest);
        System.out.println("Second largest number: " + secondLargest);

        input.close();
    }
}