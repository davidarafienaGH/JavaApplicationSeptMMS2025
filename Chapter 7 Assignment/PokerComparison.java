public class PokerComparison {

    public static void main(String[] args) {

        DeckOfCards deck = new DeckOfCards();

        deck.shuffle();

        Card[] player1 = new Card[5];
        Card[] player2 = new Card[5];

        for(int i=0;i<5;i++)
            player1[i]=deck.dealCard();

        for(int i=0;i<5;i++)
            player2[i]=deck.dealCard();

        System.out.println("PLAYER 1");
        System.out.println("----------------");

        for(Card card : player1)
            System.out.println(card);

        int rank1 = deck.evaluateHand(player1);

        System.out.println("Hand: " + deck.handName(rank1));

        System.out.println();

        System.out.println("PLAYER 2");
        System.out.println("----------------");

        for(Card card : player2)
            System.out.println(card);

        int rank2 = deck.evaluateHand(player2);

        System.out.println("Hand: " + deck.handName(rank2));

        System.out.println();

        if(rank1 > rank2)
            System.out.println("Winner: Player 1");

        else if(rank2 > rank1)
            System.out.println("Winner: Player 2");

        else
            System.out.println("Tie Game");
    }
}