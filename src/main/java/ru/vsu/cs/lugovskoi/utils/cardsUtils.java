package ru.vsu.cs.lugovskoi.utils;

import ru.vsu.cs.lugovskoi.cards.*;
import ru.vsu.cs.lugovskoi.players.Player;

import java.util.*;

public final class cardsUtils {
    private cardsUtils() {
        throw new UnsupportedOperationException();
    }

    //TODO get rid of null
    public static Combination createCombinations(List<Card> cards) {
        Combination combination = null;
        cards.sort(Comparator.comparingInt(o -> o.getRank().ordinal()));
        Map<Rank, Integer> countOfUniqueCards = new HashMap<>();
        for (Card card : cards) {
            countOfUniqueCards.put(card.getRank(), countOfUniqueCards.getOrDefault(card.getRank(), 0) + 1);
        }

        int sumCount = 0;
        int countDuplicates = 0;
        for (Integer count: countOfUniqueCards.values()) {
            if (count > 1) {
                sumCount += count;
                countDuplicates++;
            }
        }
        switch (sumCount) {
            case 0 -> combination = checkUniqueCombinations(cards);
            case 2 -> combination = Combination.PAIR;
            case 3 -> combination = Combination.THREE_OF_A_KING;
            case 4 -> combination = countDuplicates == 2 ? Combination.TWO_PAIR : Combination.FOUR_OF_A_KING;
            case 5 -> combination = Combination.FULL_HOUSE;
        }

        return combination;
    }

    //TODO rewrite sout
    public static Queue<Player> getWinner(List<Player> players) {
        Queue<Player> winners = new LinkedList<>();
        PriorityQueue<Player> candidates = new PriorityQueue<>(new CombinationComparator().reversed());
        for(Player player: players) {
            candidates.add(player);
            System.out.println(player.getCombination());
        }
        winners.add(candidates.poll());
        while (!candidates.isEmpty() && winners.peek().getCombination().equals(candidates.peek().getCombination())) {
            winners.add(candidates.poll());
        }
        return winners;
    }

    private static Combination checkUniqueCombinations(List<Card> cards) {
        int prevRank = cards.get(0).getRank().ordinal() - 1;
        Suit prevSuit = cards.get(0).getSuit();
        boolean order = true;
        boolean sameSuit = true;

        for (Card card: cards) {
            int curRank = card.getRank().ordinal();
            Suit curSuit = card.getSuit();
            if (curRank != prevRank + 1) {
                order = false;
            }
            if (curSuit != prevSuit) {
                sameSuit = false;
            }
            prevRank = curRank;
            prevSuit = curSuit;
        }

        Combination resCombination = Combination.HIGH_CARD;
        if (order || sameSuit) {
            resCombination = order ? Combination.STRAIGHT : Combination.FLUSH;
        }
        if (order && sameSuit) {
            resCombination = cards.get(0).getRank() == Rank.TEN ? Combination.ROYAL_FLUSH : Combination.STRAIGHT_FLUSH;
        }
        return resCombination;
    }

    public static Rank getRankFromString(String s) {
        if (s.length() == 2 || s.startsWith("10")) {
            for (Rank rank : Rank.values()) {
                if (rank.getName().charAt(0) == s.charAt(0)) {
                    return rank;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    public static Suit getSuitFromString(String s) {
        int ind = s.length() - 1;
        if (s.length() == 2 || s.startsWith("10")) {
            for (Suit suit : Suit.values()) {
                if (suit.getCharSuit().equals(String.valueOf(s.charAt(ind)))){
                    return suit;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    public static List<Card> listOfCards(String[] cards) {
        List<Card> res = new ArrayList<>();
        for (String card : cards) {
            res.add(new Card(card));
        }
        return res;
    }
}
