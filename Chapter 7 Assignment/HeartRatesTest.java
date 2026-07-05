import java.util.Scanner;

public class HeartRatesTest {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("First Name: ");
        String first = input.nextLine();

        System.out.print("Last Name: ");
        String last = input.nextLine();

        System.out.print("Birth Month (1-12): ");
        int month = input.nextInt();

        System.out.print("Birth Day: ");
        int day = input.nextInt();

        System.out.print("Birth Year: ");
        int year = input.nextInt();

        HeartRates person =
                new HeartRates(first, last, month, day, year);

        System.out.println("\n=========== PERSON INFORMATION ===========");

        System.out.println("Name: "
                + person.getFirstName() + " "
                + person.getLastName());

        System.out.println("Date of Birth: "
                + person.getBirthMonth() + "/"
                + person.getBirthDay() + "/"
                + person.getBirthYear());

        System.out.println("Age: "
                + person.getAge() + " years");

        System.out.println("Maximum Heart Rate: "
                + person.getMaximumHeartRate()
                + " bpm");

        System.out.printf("Target Heart Rate: %.1f - %.1f bpm%n",
                person.getMinimumTargetHeartRate(),
                person.getMaximumTargetHeartRate());

        input.close();
    }
}