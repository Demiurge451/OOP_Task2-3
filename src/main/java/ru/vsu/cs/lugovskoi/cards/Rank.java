package ru.vsu.cs.lugovskoi.cards;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public enum Rank {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("A"),
    JACK("B"),
    QUEEN("D"),
    KING("E"),
    ACE("1");

    private final String code;

    Rank(String code) {
        this.code = code;
    }

    public String getName() {
        String name;
        int rank = this.ordinal() + 2;
        if (rank <= 10) {
            name = String.valueOf(rank);
        } else {
            name = String.valueOf(String.valueOf(this).charAt(0));
        }
        return name;
    }

    public String getCode() {
        return code;
    }


}
