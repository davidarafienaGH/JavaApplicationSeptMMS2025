import java.util.List;

public class PokerDealerGame {

    public static void main(String[] args) {

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

        System.out.println("========== PLAYER ==========\n");

        for (Card card : playerHand) {
            System.out.println(card);
        }

        int playerRank = deck.evaluateHand(playerHand);

        System.out.println("\nPlayer Hand: "
                + deck.handName(playerRank));

        System.out.println("\n============================");

        System.out.println("\nDealer has been dealt five cards.");
        System.out.println("Dealer's cards are face down.");

        // Dealer chooses cards to replace
        List<Integer> replace = Dealer.cardsToReplace(dealerHand);

        for (int index : replace) {
            dealerHand[index] = deck.dealCard();
        }

        int dealerRank = deck.evaluateHand(dealerHand);

        System.out.println("\nDealer replaces "
                + replace.size() + " card(s).\n");

        System.out.println("========== DEALER ==========\n");

        for (Card card : dealerHand) {
            System.out.println(card);
        }

        System.out.println("\nDealer Hand: "
                + deck.handName(dealerRank));

        System.out.println("\n============================");

        if (playerRank > dealerRank) {

            System.out.println("\nPLAYER WINS!");

        } else if (dealerRank > playerRank) {

            System.out.println("\nDEALER WINS!");

        } else {

            System.out.println("\nDRAW!");
        }
    }
}