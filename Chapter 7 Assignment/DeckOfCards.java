import java.util.*;

public class DeckOfCards {

    private final Card[] deck = new Card[52];
    private int currentCard = 0;
    private final Random random = new Random();

    private static final String[] faces = {
            "Ace","2","3","4","5","6","7",
            "8","9","10","Jack","Queen","King"
    };

    private static final String[] suits = {
            "Hearts","Diamonds","Clubs","Spades"
    };

    public DeckOfCards() {

        int index = 0;

        for (String suit : suits) {
            for (String face : faces) {
                deck[index++] = new Card(face, suit);
            }
        }
    }

    public void shuffle() {

        currentCard = 0;

        for (int i = 0; i < deck.length; i++) {

            int j = random.nextInt(deck.length);

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

    private Map<String,Integer> faceCount(Card[] hand){

        Map<String,Integer> map = new HashMap<>();

        for(Card c : hand)
            map.put(c.getFace(), map.getOrDefault(c.getFace(),0)+1);

        return map;
    }

    public boolean hasPair(Card[] hand){

        int pairs = 0;

        for(int value : faceCount(hand).values())
            if(value == 2)
                pairs++;

        return pairs == 1;
    }

    public boolean hasTwoPairs(Card[] hand){

        int pairs = 0;

        for(int value : faceCount(hand).values())
            if(value == 2)
                pairs++;

        return pairs == 2;
    }

    public boolean hasThreeOfAKind(Card[] hand){

        for(int value : faceCount(hand).values())
            if(value == 3)
                return true;

        return false;
    }

    public boolean hasFourOfAKind(Card[] hand){

        for(int value : faceCount(hand).values())
            if(value == 4)
                return true;

        return false;
    }

    public boolean hasFlush(Card[] hand){

        String suit = hand[0].getSuit();

        for(Card c : hand)
            if(!c.getSuit().equals(suit))
                return false;

        return true;
    }

    private int value(String face){

        switch(face){

            case "Ace": return 1;
            case "2": return 2;
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            case "9": return 9;
            case "10": return 10;
            case "Jack": return 11;
            case "Queen": return 12;
            case "King": return 13;
        }

        return 0;
    }

    public boolean hasStraight(Card[] hand){

        int[] values = new int[5];

        for(int i=0;i<5;i++)
            values[i]=value(hand[i].getFace());

        Arrays.sort(values);

        for(int i=1;i<5;i++)
            if(values[i]!=values[i-1]+1)
                return false;

        return true;
    }

    public boolean hasFullHouse(Card[] hand){

        boolean two=false;
        boolean three=false;

        for(int value : faceCount(hand).values()){

            if(value==2)
                two=true;

            if(value==3)
                three=true;
        }

        return two && three;
    }

    // Ranking values
    // 7 = Four of a Kind
    // 6 = Full House
    // 5 = Flush
    // 4 = Straight
    // 3 = Three of a Kind
    // 2 = Two Pairs
    // 1 = Pair
    // 0 = High Card

    public int evaluateHand(Card[] hand){

        if(hasFourOfAKind(hand))
            return 7;

        if(hasFullHouse(hand))
            return 6;

        if(hasFlush(hand))
            return 5;

        if(hasStraight(hand))
            return 4;

        if(hasThreeOfAKind(hand))
            return 3;

        if(hasTwoPairs(hand))
            return 2;

        if(hasPair(hand))
            return 1;

        return 0;
    }

    public String handName(int rank){

        switch(rank){

            case 7: return "Four of a Kind";
            case 6: return "Full House";
            case 5: return "Flush";
            case 4: return "Straight";
            case 3: return "Three of a Kind";
            case 2: return "Two Pairs";
            case 1: return "Pair";
            default: return "High Card";
        }
    }
}