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
        for (Integer count : countOfUniqueCards.values()) {
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

    private static Combination checkUniqueCombinations(List<Card> cards) {
        int prevRank = cards.get(0).getRank().ordinal() - 1;
        Suit prevSuit = cards.get(0).getSuit();
        boolean order = true;
        boolean sameSuit = true;

        for (Card card : cards) {
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


    public static TreeSet<Integer> findKickers(Player player) {
        TreeSet<Integer> kickers = new TreeSet<>();
        for (Card card : player.getCards()) {
            int rank = card.getRank().ordinal();
            if (kickers.contains(rank)) {
                kickers.remove(rank);
            } else {
                kickers.add(rank);
            }
        }
        return kickers;
    }

    public static TreeSet<Duplicate> findDuplicates(Player player) {
        TreeSet<Duplicate> duplicates = new TreeSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (Card card : player.getCards()) {
            int rank = card.getRank().ordinal();
            map.put(rank, map.getOrDefault(rank, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int rank = entry.getKey();
            int count = entry.getValue();
            if (count != 1) {
                duplicates.add(new Duplicate(rank, count));
            }
        }
        return duplicates;
    }

    public static List<Card> listOfCards(String[] cards) {
        List<Card> res = new ArrayList<>();
        for (String card : cards) {
            res.add(new Card(card));
        }
        return res;
    }

    public static void changeCards(Player player, IReplaceCard func) {
        TreeSet<Integer> kickers = cardsUtils.findKickers(player);
        TreeSet<Duplicate> duplicates = cardsUtils.findDuplicates(player);

        int cardToChange = new Random().nextInt(5);
        Combination combination = player.getCombination();

        if (!duplicates.isEmpty() || combination == Combination.HIGH_CARD) {
            for (int i = 0; i < player.getCards().size(); i++) {
                Card card = player.getCard(i);
                int rank = card.getRank().ordinal();
                if (kickers.contains(rank) && cardToChange > 0) {
                    if ((combination == Combination.THREE_OF_A_KING || combination == Combination.FOUR_OF_A_KING)
                            && card.getRank() == Rank.ACE) {
                        continue;
                    }
                    player.setCard(i, func.replaceCard(card));
                    cardToChange--;
                }
            }
        }
    }
}
