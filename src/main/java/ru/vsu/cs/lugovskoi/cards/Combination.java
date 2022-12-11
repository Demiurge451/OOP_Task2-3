package ru.vsu.cs.lugovskoi.cards;

public enum Combination {
    HIGH_CARD(1),
    PAIR(2),
    TWO_PAIR(3),
    THREE_OF_A_KING(4),
    STRAIGHT(5),
    FLUSH(6),
    FULL_HOUSE(7),
    FOUR_OF_A_KING(8),
    STRAIGHT_FLUSH(9),
    ROYAL_FLUSH(10);

    private final int order;

    Combination(int order) {
        this.order = order;
    }
}
