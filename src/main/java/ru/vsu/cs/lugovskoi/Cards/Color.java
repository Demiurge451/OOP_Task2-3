package ru.vsu.cs.lugovskoi.Cards;

public enum Color {
    BLACK("\u001B[30m"),
    WHITE("\u001B[37m"),
    RED("\u001B[31m"),
    PURPLE("\u001B[35m");

    private final String code;
    private final String reset = "\u001B[0m";

    Color(String code) {
        this.code = code;
    }


    @Override
    public String toString() {
        return code;
    }

    public String reset() {
        return reset;
    }
}
