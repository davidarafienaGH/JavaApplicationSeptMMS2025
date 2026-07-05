import java.util.List;
import java.util.Scanner;

public class PokerPlayerGame {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

        Card[] playerHand = new Card[5];
        Card[] dealerHand = new Card[5];

        // Deal player's hand
        for (int i = 0; i < 5; i++) {
            playerHand[i] = deck.dealCard();
        }

        // Deal dealer's hand
        for (int i = 0; i < 5; i++) {
            dealerHand[i] = deck.dealCard();
        }

        System.out.println("========== YOUR HAND ==========\n");

        for (int i = 0; i < 5; i++) {
            System.out.println((i + 1) + ". " + playerHand[i]);
        }

        System.out.println("\nEnter how many cards you want to replace (0-3): ");
        int number = input.nextInt();

        if (number > 3)
            number = 3;

        for (int i = 0; i < number; i++) {

            System.out.print("Card number to replace (1-5): ");
            int position = input.nextInt();

            if (position >= 1 && position <= 5) {
                playerHand[position - 1] = deck.dealCard();
            }
        }

        // Dealer plays automatically
        List<Integer> replace = Dealer.cardsToReplace(dealerHand);

        for (int index : replace) {
            dealerHand[index] = deck.dealCard();
        }

        int playerRank = deck.evaluateHand(playerHand);
        int dealerRank = deck.evaluateHand(dealerHand);

        System.out.println("\n========== FINAL PLAYER HAND ==========\n");

        for (Card card : playerHand) {
            System.out.println(card);
        }

        System.out.println("\nPlayer: "
                + deck.handName(playerRank));

        System.out.println("\n========== DEALER HAND ==========\n");

        for (Card card : dealerHand) {
            System.out.println(card);
        }

        System.out.println("\nDealer: "
                + deck.handName(dealerRank));

        System.out.println("\n========== RESULT ==========\n");

        if (playerRank > dealerRank)
            System.out.println("You Win!");

        else if (dealerRank > playerRank)
            System.out.println("Dealer Wins!");

        else
            System.out.println("Draw!");

        input.close();
    }
}