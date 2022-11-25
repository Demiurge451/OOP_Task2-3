package ru.vsu.cs.lugovskoi.Players;

import ru.vsu.cs.lugovskoi.Bets.Chip;
import ru.vsu.cs.lugovskoi.Cards.Card;
import ru.vsu.cs.lugovskoi.Cards.Combination;
import ru.vsu.cs.lugovskoi.Cards.Rank;
import ru.vsu.cs.lugovskoi.Cards.Suit;

import java.util.*;

public class Player {
    private String name;
    Map<Chip, Integer> chips = new TreeMap<>(Comparator.comparingInt(Chip::value));
    private final int countChips = 50;
    private final int sum;

    private Card[] cards = new Card[5];
    private Combination combination;

    public Player(String name) {
        this.name = name;
        int curSum = 0;
        chips.put(new Chip(5), 10);
        chips.put(new Chip(10), 10);
        chips.put(new Chip(25), 10);
        chips.put(new Chip(50), 10);
        chips.put(new Chip(100), 10);
        for (Map.Entry<Chip, Integer> element : chips.entrySet()) {
            curSum += element.getKey().value() * element.getValue();
        }
        sum = curSum;
    }

    public void createCombinations() {
        Arrays.sort(cards, Comparator.comparingInt(o -> o.getRank().ordinal()));
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
            case 0 -> combination = checkUniqueCombinations();
            case 2 -> combination = Combination.PAIR;
            case 3 -> combination = Combination.THREE_OF_A_KING;
            case 4 -> combination = countDuplicates == 2 ? Combination.TWO_PAIR : Combination.FOUR_OF_A_KING;
            case 5 -> combination = Combination.FULL_HOUSE;
        }
    }

    private Combination checkUniqueCombinations() {
        int prevRank = -1;
        Suit prevSuit = cards[0].getSuit();
        boolean order = true;
        boolean sameSuit = true;

        for (Card card: cards) {
            int curRank = card.getRank().ordinal();
            Suit curSuit = card.getSuit();
            if (curRank == prevRank + 1) {
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
            resCombination = cards[0].getRank() == Rank.TEN ? Combination.ROYAL_FLUSH : Combination.STRAIGHT_FLUSH;
        }
        return resCombination;
    }

    public int getCountChips() {
        return countChips;
    }

    public Combination getCombination() {
        return combination;
    }

    public void setCard(int ind, Card card) {
        checkIndex(ind);
        cards[ind] = card;
    }

    public String getName() {
        return name;
    }

    public Card getCard(int ind) {
        checkIndex(ind);
        return cards[ind];
    }

    public Card[] getCards() {
        return cards;
    }

    private void checkIndex(int ind) {
        if (ind < 0 || ind > 5) {
            throw new IndexOutOfBoundsException();
        }
    }
}
