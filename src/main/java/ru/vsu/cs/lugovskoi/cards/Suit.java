package ru.vsu.cs.lugovskoi.cards;

public enum Suit {
    SPADES("\\uD83C", "\\uDCA", Color.BLACK),
    CLUBS("\\uD83C", "\\uDCD", Color.WHITE),
    DIAMONDS("\\uD83C", "\\uDCC", Color.PURPLE),
    HEARTS("\\uD83C", "\\uDCB", Color.RED);

    private final String code;
    private final Color color;

    Suit(String unicode, String suit, Color color) {
        this.color = color;
        code = unicode + suit;
    }

    public String getCode() {
        return code;
    }

    public Color getColor() {
        return color;
    }
}
