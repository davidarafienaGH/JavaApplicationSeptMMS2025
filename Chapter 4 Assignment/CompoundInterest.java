public class CompoundInterest {
    public static void main(String[] args) {

        double principal = 1000.0;

        for (int rate = 5; rate <= 10; rate++) {

            System.out.println("\nInterest Rate: " + rate + "%");
            System.out.printf("%s%20s%n", "Year", "Amount");

            for (int year = 1; year <= 10; year++) {

                double amount =
                    principal * Math.pow(1.0 + rate / 100.0, year);

                System.out.printf("%4d%20.2f%n",
                                  year, amount);
            }
        }
    }
}