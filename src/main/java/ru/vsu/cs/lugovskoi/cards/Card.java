package ru.vsu.cs.lugovskoi.cards;

import ru.vsu.cs.lugovskoi.utils.*;
public class Card {
    private final Suit suit;
    private final Rank rank;

    private final String unicode;


    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.unicode = suit.getCode() + rank.getCode();
    }


    @Override
    public String toString(){
        String suitSym = unicode.substring(2, 6);
        String rankSym = unicode.substring(8, 12);
        suitSym = Character.toString((char) Integer.parseInt(suitSym, 16));
        rankSym = Character.toString((char) Integer.parseInt(rankSym, 16));
        return suit.getColor() + suitSym + rankSym + this.rank.getName() + suit.getColor().reset();
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
}
