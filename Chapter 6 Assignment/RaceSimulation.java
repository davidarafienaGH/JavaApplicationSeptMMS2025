import java.util.Random;

public class RaceSimulation {
    public static void main(String[] args) {
        Random rand = new Random();
        int tortoise = 1;
        int hare = 1;

        System.out.println("BANG !!!!!");
        System.out.println("AND THEY'RE OFF !!!!!");

        while (tortoise < 70 && hare < 70) {
            int tick = 1 + rand.nextInt(10);
            
            // Tortoise logic
            if (tick <= 5) tortoise += 3;       // Fast plod
            else if (tick <= 7) tortoise -= 6;  // Slip
            else tortoise += 1;                 // Slow plod
            if (tortoise < 1) tortoise = 1;

            // Hare logic
            if (tick <= 2) { /* Sleep, do nothing */ }
            else if (tick <= 4) hare += 9;      // Big hop
            else if (tick == 5) hare -= 12;     // Big slip
            else if (tick <= 8) hare += 1;      // Small hop
            else hare -= 2;                     // Small slip
            if (hare < 1) hare = 1;

            // Render Track Line
            for (int pos = 1; pos <= 70; pos++) {
                if (pos == tortoise && pos == hare) {
                    System.out.print("OUCH!!!");
                    pos += 6; // Account for the extra characters printed
                } else if (pos == tortoise) {
                    System.out.print("T");
                } else if (pos == hare) {
                    System.out.print("H");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        if (tortoise >= 70 && hare >= 70) {
            System.out.println("It's a tie.");
        } else if (tortoise >= 70) {
            System.out.println("TORTOISE WINS!!! YAY!!!");
        } else {
            System.out.println("Hare wins. Yuch.");
        }
    }
}