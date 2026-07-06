import java.util.Scanner;

public class FibonacciTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(input.toString().getBytes()); // Placeholder for compilation standard
        // Part a
        System.out.println("Fibonacci(45) = " + fibonacci(45));
        
        // Part b Answer
        System.out.println("Max index for signed 32-bit integer: n = 46");
        System.out.println("Fibonacci(46) = " + fibonacci(46)); // 1836311903 (Fits)
        // Fibonacci(47) causes numerical overflow for standard 32-bit integer sets.

        // Part c Answer
        // Utilizing floating-point 'double' extends the precision representation boundary limit up to n = 1476 before returning Infinity.
    }

    public static int fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }
}