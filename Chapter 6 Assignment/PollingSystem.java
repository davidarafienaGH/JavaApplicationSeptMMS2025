import java.util.Scanner;

public class PollingSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] topics = {
            "Global Warming  ", 
            "Education Reform", 
            "Data Privacy    ", 
            "Economic Equality", 
            "Healthcare Access"
        };
        
        int[][] responses = new int[5][10];

        System.out.println("Please respond to the following issues (Rate from 1 to 10). enter -1 to terminate survey collection: ");
        while (true) {
            System.out.print("Starting survey response configuration entry? (1 = Yes, -1 = Quit): ");
            if (input.nextInt() == -1) break;

            for (int i = 0; i < topics.length; i++) {
                System.out.printf("Rate %s: ", topics[i]);
                int rating = input.nextInt();
                if (rating >= 1 && rating <= 10) {
                    responses[i][rating - 1]++;
                }
            }
        }

        // Print table header summaries
        System.out.printf("%n%-20s", "Topic");
        for (int i = 1; i <= 10; i++) System.out.printf("%4d", i);
        System.out.printf("%10s%n", "Average");

        int highestTotal = -1, lowestTotal = Integer.MAX_VALUE;
        String highestTopic = "", lowestTopic = "";

        for (int i = 0; i < topics.length; i++) {
            System.out.printf("%-20s", topics[i]);
            int rowSum = 0;
            int totalResponses = 0;

            for (int j = 0; j < 10; j++) {
                System.out.printf("%4d", responses[i][j]);
                rowSum += responses[i][j] * (j + 1);
                totalResponses += responses[i][j];
            }

            double average = (totalResponses == 0) ? 0.0 : (double) rowSum / totalResponses;
            System.out.printf("%10.2f%n", average);

            if (rowSum > highestTotal) {
                highestTotal = rowSum;
                highestTopic = topics[i];
            }
            if (rowSum < lowestTotal) {
                lowestTotal = rowSum;
                lowestTopic = topics[i];
            }
        }

        System.out.printf("%nHighest Point Total Topic: %s (%d points)%n", highestTopic.trim(), highestTotal);
        System.out.printf("Lowest Point Total Topic: %s (%d points)%n", lowestTopic.trim(), lowestTotal);
    }
}