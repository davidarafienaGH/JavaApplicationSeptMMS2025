public class SumCommandLineArgs {
    public static void main(String[] args) {
        double total = 0.0;
        for (String arg : args) {
            total += Double.parseDouble(arg);
        }
        System.out.printf("The sum of arguments is: %.2f%n", total);
    }
}