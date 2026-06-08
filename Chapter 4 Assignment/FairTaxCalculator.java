import java.util.Scanner;

public class FairTaxCalculator {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        double totalExpenses = 0;

        System.out.print("Housing: ");
        totalExpenses += input.nextDouble();

        System.out.print("Food: ");
        totalExpenses += input.nextDouble();

        System.out.print("Clothing: ");
        totalExpenses += input.nextDouble();

        System.out.print("Transportation: ");
        totalExpenses += input.nextDouble();

        System.out.print("Education: ");
        totalExpenses += input.nextDouble();

        System.out.print("Health Care: ");
        totalExpenses += input.nextDouble();

        System.out.print("Vacations: ");
        totalExpenses += input.nextDouble();

        double fairTax = totalExpenses * 0.23;

        System.out.printf(
            "%nTotal Expenses: $%.2f%n", totalExpenses);

        System.out.printf(
            "Estimated FairTax (23%%): $%.2f%n", fairTax);
    }
}