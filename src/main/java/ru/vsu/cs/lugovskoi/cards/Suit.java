package ru.vsu.cs.lugovskoi.cards;

public enum Suit {
    SPADES("\\uD83C", "\\uDCA", "S", Color.BLACK),
    CLUBS("\\uD83C", "\\uDCD", "C", Color.WHITE),
    DIAMONDS("\\uD83C", "\\uDCC", "D", Color.PURPLE),
    HEARTS("\\uD83C", "\\uDCB", "H", Color.RED);

    private final String code;
    private final String charSuit;
    private final Color color;

    Suit(String unicode, String suit, String charSuit, Color color) {
        this.color = color;
        code = unicode + suit;
        this.charSuit = charSuit;
    }

    public String getCode() {
        return code;
    }

    public String getCharSuit() {
        return charSuit;
    }

    public Color getColor() {
        return color;
    }
}
