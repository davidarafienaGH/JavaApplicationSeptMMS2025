public class FacebookGrowth {

    public static void main(String[] args) {

        double users = 1_000_000_000.0;
        int months = 0;

        while (users < 1_500_000_000.0) {
            users *= 1.04;
            months++;
        }

        System.out.println(
            "Months to reach 1.5 billion: " + months);

        users = 1_000_000_000.0;
        months = 0;

        while (users < 2_000_000_000.0) {
            users *= 1.04;
            months++;
        }

        System.out.println(
            "Months to reach 2 billion: " + months);
    }
}