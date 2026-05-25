public class CheckerboardPattern {

    public static void main(String[] args) {

        for (int row = 1; row <= 8; row++) {

            // Print leading space for even rows
            if (row % 2 == 0) {
                System.out.print(" ");
            }

            // Print 8 asterisks
            for (int column = 1; column <= 8; column++) {
                System.out.print("* ");
            }

            // Move to next line
            System.out.println();
        }
    }
}