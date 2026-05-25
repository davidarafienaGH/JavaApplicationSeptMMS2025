import java.util.Scanner;

public class SalesCommissionCalculator {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        double totalSales = 0.0;

        System.out.println("Items Available:");
        System.out.println("1 - $239.99");
        System.out.println("2 - $129.75");
        System.out.println("3 - $99.95");
        System.out.println("4 - $350.89");

        while (true) {

            System.out.print("\nEnter item number sold (1-4) or 0 to finish: ");
            int item = input.nextInt();

            if (item == 0) {
                break;
            }

            switch (item) {
                case 1:
                    totalSales += 239.99;
                    break;

                case 2:
                    totalSales += 129.75;
                    break;

                case 3:
                    totalSales += 99.95;
                    break;

                case 4:
                    totalSales += 350.89;
                    break;

                default:
                    System.out.println("Invalid item number.");
            }
        }

        double earnings = 200 + (0.09 * totalSales);

        System.out.printf("\nTotal Sales: $%.2f%n", totalSales);
        System.out.printf("Weekly Earnings: $%.2f%n", earnings);

        input.close();
    }
}