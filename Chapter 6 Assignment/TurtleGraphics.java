import java.util.Scanner;

public class TurtleGraphics {
    private static final int[][] floor = new int[20][20];
    private static int currentRow = 0;
    private static int currentCol = 0;
    private static boolean penDown = false;
    // Directions: 0 = Right, 1 = Down, 2 = Left, 3 = Up
    private static int direction = 0; 

    public static void main(String[] args) {
        int[] commands = {2, 5, 12, 3, 5, 12, 3, 5, 12, 3, 5, 12, 1, 6, 9};
        int i = 0;

        while (i < commands.length) {
            int cmd = commands[i++];
            switch (cmd) {
                case 1: penDown = false; break;
                case 2: penDown = true; break;
                case 3: direction = (direction + 1) % 4; break; // Turn Right
                case 4: direction = (direction + 3) % 4; break; // Turn Left
                case 5:
                    int steps = commands[i++];
                    moveTurtle(steps);
                    break;
                case 6: displayFloor(); break;
                case 9: return; // End of data
            }
        }
    }

    private static void moveTurtle(int steps) {
        for (int s = 0; s < steps; s++) {
            if (penDown) {
                floor[currentRow][currentCol] = 1;
            }
            
            switch (direction) {
                case 0: if (currentCol < 19) currentCol++; break; // Right
                case 1: if (currentRow < 19) currentRow++; break; // Down
                case 2: if (currentCol > 0) currentCol--; break;  // Left
                case 3: if (currentRow > 0) currentRow--; break;  // Up
            }
        }
    }

    private static void displayFloor() {
        for (int[] row : floor) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "*" : " ");
            }
            System.out.println();
        }
    }
}