import java.util.Scanner;

public class SmallestValue {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("How many numbers? ");
        int count = input.nextInt();

        System.out.print("Enter number: ");
        int smallest = input.nextInt();

        for (int i = 2; i <= count; i++) {
            System.out.print("Enter number: ");
            int value = input.nextInt();

            if (value < smallest)
                smallest = value;
        }

        System.out.println("Smallest value: " + smallest);
    }
}