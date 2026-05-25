import java.util.Scanner;

public class SalaryCalculator {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        for (int employee = 1; employee <= 3; employee++) {

            System.out.println("Employee " + employee);

            System.out.print("Enter hours worked: ");
            double hoursWorked = input.nextDouble();

            System.out.print("Enter hourly rate: ");
            double hourlyRate = input.nextDouble();

            double grossPay;

            if (hoursWorked <= 40) {
                grossPay = hoursWorked * hourlyRate;
            } else {
                double overtimeHours = hoursWorked - 40;
                grossPay = (40 * hourlyRate) +
                           (overtimeHours * hourlyRate * 1.5);
            }

            System.out.printf("Gross Pay: $%.2f%n%n", grossPay);
        }

        input.close();
    }
}