public class ShuffleTest {

    public static void main(String[] args) {

        DeckOfCards deck = new DeckOfCards();

        deck.shuffle();

        System.out.println("Shuffled Deck\n");

        for (int i = 0; i < 52; i++) {

            System.out.println(deck.dealCard());

        }

    }

}