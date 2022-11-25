package ru.vsu.cs.lugovskoi.Cards;

public enum Rank {
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
        int rank = this.ordinal() + 6;
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
