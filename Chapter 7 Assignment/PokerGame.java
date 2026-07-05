public class PokerGame {

    public static void main(String[] args) {

        DeckOfCards deck = new DeckOfCards();

        deck.shuffle();

        Card[] hand = new Card[5];

        System.out.println("Five Card Poker Hand");
        System.out.println("---------------------");

        for(int i=0;i<5;i++){

            hand[i]=deck.dealCard();
            System.out.println(hand[i]);
        }

        System.out.println();

        if(deck.hasPair(hand))
            System.out.println("Pair");

        if(deck.hasTwoPairs(hand))
            System.out.println("Two Pairs");

        if(deck.hasThreeOfAKind(hand))
            System.out.println("Three of a Kind");

        if(deck.hasFourOfAKind(hand))
            System.out.println("Four of a Kind");

        if(deck.hasFlush(hand))
            System.out.println("Flush");

        if(deck.hasStraight(hand))
            System.out.println("Straight");

        if(deck.hasFullHouse(hand))
            System.out.println("Full House");

        if(!(deck.hasPair(hand)
                || deck.hasTwoPairs(hand)
                || deck.hasThreeOfAKind(hand)
                || deck.hasFourOfAKind(hand)
                || deck.hasFlush(hand)
                || deck.hasStraight(hand)
                || deck.hasFullHouse(hand)))
        {
            System.out.println("High Card");
        }
    }
}