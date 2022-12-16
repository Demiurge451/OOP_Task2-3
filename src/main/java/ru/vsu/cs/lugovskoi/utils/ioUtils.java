package ru.vsu.cs.lugovskoi.utils;

import ru.vsu.cs.lugovskoi.cards.Rank;
import ru.vsu.cs.lugovskoi.cards.Suit;


public final class ioUtils {
    private ioUtils() {
        throw new UnsupportedOperationException();
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
                if (suit.getCharSuit().equals(String.valueOf(s.charAt(ind)))) {
                    return suit;
                }
            }
        }
        throw new IllegalArgumentException();
    }


}
