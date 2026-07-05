import java.util.Random;

public class DeckOfCards2{

    private Card[] deck = new Card[52];
    private int currentCard = 0;

    private Random random = new Random();

    private Face[] faces = Face.values();
    private Suit[] suits = Suit.values();

    public DeckOfCards() {

        int index = 0;

        for (Suit suit : suits) {

            for (Face face : faces) {

                deck[index++] = new Card(face, suit);

            }

        }

    }

    // Fisher–Yates Shuffle
    public void shuffle() {

        currentCard = 0;

        for (int i = deck.length - 1; i > 0; i--) {

            int j = random.nextInt(i + 1);

            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;

        }

    }

    public Card dealCard() {

        if (currentCard < deck.length)
            return deck[currentCard++];

        return null;

    }
}