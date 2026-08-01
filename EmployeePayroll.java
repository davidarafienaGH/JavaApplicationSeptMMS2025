import java.util.Scanner;

public class EmployeePayroll {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter employee name: ");
        String employeeName = scan.nextLine();

        System.out.print("Enter total hours worked: ");
        int hoursWorked = scan.nextInt();

        System.out.print("Enter hourly rate: ");
        int hourlyRate = scan.nextInt();

        int grossSalary = hoursWorked * hourlyRate;
        double tax = grossSalary * 0.10;
        double netSalary = grossSalary - tax;

        System.out.println("Employee Name: " + employeeName);
        System.out.println("Gross Salary: " + grossSalary);
        System.out.println("Tax: " + tax);
        System.out.println("Net Salary: " + netSalary);
    }
}