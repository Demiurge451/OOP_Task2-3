package ru.vsu.cs.lugovskoi;

import ru.vsu.cs.lugovskoi.cards.Card;
import ru.vsu.cs.lugovskoi.cards.Combination;
import ru.vsu.cs.lugovskoi.game.Poker;
import ru.vsu.cs.lugovskoi.players.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ru.vsu.cs.lugovskoi.utils.cardsUtils;

public class Main {
    public static void main(String[] args) {
        Poker poker = new Poker(3);
        List<Card> cards = cardsUtils.listOfCards(new String[]{"9h", "8h", "7h", "6h", "10h"});
        Player p1 = new Player("Player1", cards);
        System.out.println(p1.getCombination());
        poker.startGame();
    }
}
