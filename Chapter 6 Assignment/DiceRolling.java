import java.util.Random;

public class DiceRolling {
    public static void main(String[] args) {
        Random random = new Random();
        int[] frequencies = new int[13]; // Indices 2 to 12 hold totals

        for (int roll = 1; roll <= 36_000_000; roll++) {
            int die1 = 1 + random.nextInt(6);
            int die2 = 1 + random.nextInt(6);
            frequencies[die1 + die2]++;
        }

        System.out.printf("%s%12s%n", "Sum", "Frequency");
        for (int sum = 2; sum <= 12; sum++) {
            System.out.printf("%3d%12d%n", sum, frequencies[sum]);
        }
    }
}