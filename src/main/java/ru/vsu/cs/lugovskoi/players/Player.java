package ru.vsu.cs.lugovskoi.players;

import ru.vsu.cs.lugovskoi.cards.Card;
import ru.vsu.cs.lugovskoi.cards.Combination;
import ru.vsu.cs.lugovskoi.utils.cardsUtils;

import java.util.*;

public class Player {
    private final String name;
    private final Map<Chip, Integer> chips = new TreeMap<>((o1, o2) -> Integer.compare(o2.value(), o1.value()));

    private final List<Card> cards;
    private Combination combination;

    public Player(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
        chips.put(new Chip(5), 10);
        chips.put(new Chip(10), 10);
        chips.put(new Chip(25), 10);
        chips.put(new Chip(50), 10);
        chips.put(new Chip(100), 10);
        combination = cardsUtils.createCombinations(cards);
    }

    public Combination getCombination() {
        return combination;
    }

    public void setCard(int ind, Card card) {
        checkIndex(ind);
        cards.set(ind, card);
        setCombination(cardsUtils.createCombinations(cards));
    }

    public String getName() {
        return name;
    }

    public Card getCard(int ind) {
        checkIndex(ind);
        return cards.get(ind);
    }

    public List<Card> getCards() {
        return cards;
    }

    private void checkIndex(int ind) {
        if (ind < 0 || ind > 5) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void setCombination(Combination combination) {
        this.combination = combination;
    }

    public Map<Chip, Integer> getChips() {
        return chips;
    }

    public int takeChip(Chip chip, int curBet) {
        int bet = 0;
        if (chips.get(chip) == null) {
            throw new IllegalArgumentException();
        }
        while (curBet > bet + chip.value()) {
            if (chips.get(chip) != 0) {
                bet += chip.value();
                chips.put(chip, chips.get(chip) - 1);
            } else {
                break;
            }
        }

        return bet;
    }

    @Override
    public String toString() {
        return name;
    }
}
