package ru.vsu.cs.lugovskoi;

import ru.vsu.cs.lugovskoi.game.Poker;


public class Main {
    public static void main(String[] args) {
        Poker poker = new Poker(8, 3);
        poker.startGame();
    }
}
