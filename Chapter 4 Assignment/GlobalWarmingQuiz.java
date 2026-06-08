import java.util.Scanner;

public class GlobalWarmingQuiz {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int score = 0;

        String[] questions = {
            "1. Primary greenhouse gas emitted by human activities?\n"
            + "1) Oxygen\n2) Carbon dioxide\n3) Nitrogen\n4) Helium",

            "2. Which organization shared the 2007 Nobel Peace Prize with Al Gore?\n"
            + "1) NASA\n2) NOAA\n3) IPCC\n4) Greenpeace",

            "3. Which statement is commonly made by climate skeptics?\n"
            + "1) Climate has never changed\n"
            + "2) Humans have no effect whatsoever\n"
            + "3) Natural variability may contribute to warming\n"
            + "4) CO2 does not exist",

            "4. Scientific consensus among major scientific organizations?\n"
            + "1) Earth is cooling\n"
            + "2) Humans contribute significantly to warming\n"
            + "3) Climate change is impossible\n"
            + "4) Volcanoes produce all greenhouse gases",

            "5. Which is commonly used as climate evidence?\n"
            + "1) Temperature records\n"
            + "2) Astrology charts\n"
            + "3) Planetary alignment\n"
            + "4) Moon phases"
        };

        int[] answers = {2, 3, 3, 2, 1};

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.print("Answer: ");
            int response = input.nextInt();

            if (response == answers[i])
                score++;
        }

        System.out.println("\nCorrect answers: " + score);

        if (score == 5)
            System.out.println("Excellent");
        else if (score == 4)
            System.out.println("Very good");
        else {
            System.out.println(
                "Time to brush up on your knowledge of global warming.");

            System.out.println("Suggested sources:");
            System.out.println("https://www.ipcc.ch");
            System.out.println("https://climate.nasa.gov");
        }
    }
}