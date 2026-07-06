import java.util.Scanner;

public class AirlineReservation {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean[] seats = new boolean[10]; // False by default

        while (true) {
            System.out.print("Please type 1 for First Class or 2 for Economy: ");
            int choice = input.nextInt();
            
            if (choice == 1) {
                assignSeat(seats, 0, 5, "First Class", input);
            } else if (choice == 2) {
                assignSeat(seats, 5, 10, "Economy", input);
            }
            System.out.println();
        }
    }

    private static void assignSeat(boolean[] seats, int start, int end, String section, Scanner input) {
        for (int i = start; i < end; i++) {
            if (!seats[i]) {
                seats[i] = true;
                displayBoardingPass(i + 1, section);
                return;
            }
        }
        
        // Section is full
        String alternateSection = section.equals("First Class") ? "Economy" : "First Class";
        System.out.printf("%s section is full. Would you accept a seat in %s? (1 for Yes, 2 for No): ", section, alternateSection);
        int response = input.nextInt();
        
        if (response == 1) {
            int newStart = section.equals("First Class") ? 5 : 0;
            int newEnd = section.equals("First Class") ? 10 : 5;
            for (int i = newStart; i < newEnd; i++) {
                if (!seats[i]) {
                    seats[i] = true;
                    displayBoardingPass(i + 1, alternateSection);
                    return;
                }
            }
            System.out.println("The entire plane is full.");
        } else {
            System.out.println("Next flight leaves in 3 hours.");
        }
    }

    private static void displayBoardingPass(int seatNum, String section) {
        System.out.println("\n--- BOARDING PASS ---");
        System.out.printf("Seat Number: %d%n", seatNum);
        System.out.printf("Section: %s%n", section);
        System.out.println("---------------------");
    }
}