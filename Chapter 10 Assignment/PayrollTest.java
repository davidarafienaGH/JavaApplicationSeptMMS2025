import java.util.Calendar;

public class PayrollTest {
    public static void main(String[] args) {
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;

        Employee[] employees = new Employee[2];
        
        employees[0] = new SalariedEmployee("John", "Doe", "111-11-1111", 
            new Date(currentMonth, 15, 1990), 1000.00);
        employees[1] = new SalariedEmployee("Jane", "Smith", "222-22-2222", 
            new Date((currentMonth % 12) + 1, 10, 1992), 1200.00);

        System.out.println("Processing Payroll for Month " + currentMonth + ":\n");

        for (Employee emp : employees) {
            double totalEarnings = emp.earnings();
            System.out.println(emp);

            if (emp.getBirthDate() != null && emp.getBirthDate().getMonth() == currentMonth) {
                totalEarnings += 100.00;
                System.out.println("*** Birthday Bonus of $100.00 Applied! ***");
            }

            System.out.printf("Total Payable: $%,.2f%n%n", totalEarnings);
        }
    }
}
