public static int getQuotient(int a, int b) { return a / b; }
public static int getRemainder(int a, int b) { return a % b; }

public static void displayDigits(int number) {
    String result = "";
    while (number > 0) {
        int digit = getRemainder(number, 10);
        result = digit + "  " + result;
        number = getQuotient(number, 10);
    }
    System.out.println(result.trim());
}