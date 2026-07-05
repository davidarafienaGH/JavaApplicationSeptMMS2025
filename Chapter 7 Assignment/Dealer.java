import java.util.*;

public class Dealer {

    public static List<Integer> cardsToReplace(Card[] hand) {

        Map<String, Integer> count = new HashMap<>();

        for (Card card : hand) {
            count.put(card.getFace(),
                    count.getOrDefault(card.getFace(), 0) + 1);
        }

        boolean hasFour = false;
        boolean hasThree = false;
        int pairs = 0;

        for (int value : count.values()) {

            if (value == 4)
                hasFour = true;

            if (value == 3)
                hasThree = true;

            if (value == 2)
                pairs++;
        }

        List<Integer> replace = new ArrayList<>();

        // Four of a Kind
        if (hasFour)
            return replace;

        // Full House
        if (hasThree && pairs == 1)
            return replace;

        // Three of a Kind
        if (hasThree) {

            for (int i = 0; i < hand.length; i++) {

                if (count.get(hand[i].getFace()) != 3)
                    replace.add(i);
            }

            return replace;
        }

        // Two Pairs
        if (pairs == 2) {

            for (int i = 0; i < hand.length; i++) {

                if (count.get(hand[i].getFace()) == 1)
                    replace.add(i);
            }

            return replace;
        }

        // One Pair
        if (pairs == 1) {

            for (int i = 0; i < hand.length; i++) {

                if (count.get(hand[i].getFace()) == 1)
                    replace.add(i);
            }

            return replace;
        }

        // High Card
        int highest = 0;
        int highestIndex = 0;

        for (int i = 0; i < hand.length; i++) {

            int value = faceValue(hand[i].getFace());

            if (value > highest) {
                highest = value;
                highestIndex = i;
            }
        }

        for (int i = 0; i < hand.length; i++) {

            if (i != highestIndex)
                replace.add(i);
        }

        // Maximum of three cards
        while (replace.size() > 3)
            replace.remove(replace.size() - 1);

        return replace;
    }

    private static int faceValue(String face) {

        switch (face) {

            case "Ace":
                return 14;
            case "King":
                return 13;
            case "Queen":
                return 12;
            case "Jack":
                return 11;
            default:
                return Integer.parseInt(face);
        }
    }
}