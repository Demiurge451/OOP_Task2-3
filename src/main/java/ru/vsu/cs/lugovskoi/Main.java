package ru.vsu.cs.lugovskoi;

import ru.vsu.cs.lugovskoi.Cards.Card;
import ru.vsu.cs.lugovskoi.Game.Poker;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;


public class Main {
    public static void main(String[] args) {
        Poker poker = new Poker(3);
        poker.startGame();
    }
}
