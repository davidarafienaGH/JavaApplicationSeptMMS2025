import java.util.Scanner;

public class DuplicateElimination {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] uniqueValues = new int[5];
        int count = 0;

        while (count < 5) {
            System.out.print("Enter a number between 10 and 100: ");
            int number = input.nextInt();

            if (number >= 10 && number <= 100) {
                boolean isDuplicate = false;
                for (int i = 0; i < count; i++) {
                    if (uniqueValues[i] == number) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (!isDuplicate) {
                    uniqueValues[count] = number;
                    count++;
                }

                // Display unique values read so far
                System.out.print("Unique values: ");
                for (int i = 0; i < count; i++) {
                    System.out.print(uniqueValues[i] + " ");
                }
                System.out.println();
            } else {
                System.out.println("Number must be between 10 and 100.");
            }
        }
    }
}