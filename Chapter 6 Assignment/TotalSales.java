import java.util.Scanner;

public class TotalSales {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Rows: Products 1-5 (indices 0-4). Columns: Salespeople 1-4 (indices 0-3)
        double[][] sales = new double[5][4];

        System.out.println("Enter slip data (Salesperson [1-4], Product [1-5], Amount). Enter -1 for salesperson to stop.");
        while (true) {
            System.out.print("Enter salesperson number: ");
            int sp = input.nextInt();
            if (sp == -1) break;
            
            System.out.print("Enter product number: ");
            int prod = input.nextInt();
            System.out.print("Enter total dollar value: ");
            double value = input.nextDouble();

            if (sp >= 1 && sp <= 4 && prod >= 1 && prod <= 5) {
                sales[prod - 1][sp - 1] += value;
            } else {
                System.out.println("Invalid input!");
            }
        }

        // Print table header
        System.out.printf("%n%-10s%10s%10s%10s%10s%12s%n", "Product", "SP 1", "SP 2", "SP 3", "SP 4", "Total");
        
        double[] colTotals = new double[4];
        for (int i = 0; i < 5; i++) {
            double rowTotal = 0.0;
            System.out.printf("Prod %-5d", i + 1);
            for (int j = 0; j < 4; j++) {
                System.out.printf("%10.2f", sales[i][j]);
                rowTotal += sales[i][j];
                colTotals[j] += sales[i][j];
            }
            System.out.printf("%12.2f%n", rowTotal);
        }

        System.out.printf("%-10s", "Total");
        double grandTotal = 0.0;
        for (double colTotal : colTotals) {
            System.out.printf("%10.2f", colTotal);
            grandTotal += colTotal;
        }
        System.out.printf("%12.2f%n", grandTotal);
    }
}