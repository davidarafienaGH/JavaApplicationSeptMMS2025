public class VariableLengthArgList {
    public static int product(int... numbers) {
        if (numbers.length == 0) return 0;
        int result = 1;
        for (int num : numbers) {
            result *= num;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.printf("Product of 2 and 3: %d%n", product(2, 3));
        System.out.printf("Product of 1, 3, 5, 7: %d%n", product(1, 3, 5, 7));
        System.out.printf("Product of empty argument list: %d%n", product());
    }
}