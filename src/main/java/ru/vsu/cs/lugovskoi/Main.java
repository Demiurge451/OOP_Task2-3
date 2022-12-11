package ru.vsu.cs.lugovskoi;

import ru.vsu.cs.lugovskoi.cards.Card;
import ru.vsu.cs.lugovskoi.cards.Combination;
import ru.vsu.cs.lugovskoi.game.Poker;
import ru.vsu.cs.lugovskoi.players.Player;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Poker poker = new Poker(3);
        poker.startGame();
    }
}
