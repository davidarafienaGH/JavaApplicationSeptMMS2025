import java.util.Arrays;

public class Sieve {
    public static void main(String[] args) {
        boolean[] primes = new boolean[1000];
        Arrays.fill(primes, true);

        for (int i = 2; i < Math.sqrt(primes.length); i++) {
            if (primes[i]) {
                // Set all multiples of i to false
                for (int k = i * i; k < primes.length; k += i) {
                    primes[k] = false;
                }
            }
        }

        System.out.println("Prime numbers between 2 and 999:");
        int count = 0;
        for (int i = 2; i < primes.length; i++) {
            if (primes[i]) {
                System.out.printf("%4d", i);
                count++;
                if (count % 10 == 0) System.out.println();
            }
        }
    }
}