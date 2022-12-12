package ru.vsu.cs.lugovskoi;

import org.junit.jupiter.api.Test;
import ru.vsu.cs.lugovskoi.players.Player;
import ru.vsu.cs.lugovskoi.utils.cardsUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCombination {
    @Test
    void HighCard() {
        Player p1 = new Player("Player1", cardsUtils.listOfCards(new String[]{"6H", "7H", "8D", "9D", "KD"}));
        Player p2 = new Player("Player2", cardsUtils.listOfCards(new String[]{"6D", "7D", "8H", "9H", "JH"}));
        Player p3 = new Player("Player3", cardsUtils.listOfCards(new String[]{"6C", "7C", "8C", "9S", "QS"}));
        Player p4 = new Player("Player4", cardsUtils.listOfCards(new String[]{"6S", "7S", "8S", "9C", "AC"}));

        List<Player> candidates = List.of(p1, p2, p3, p4);
        assertEquals(p4, cardsUtils.getWinner(candidates).poll());
    }

    @Test
    void Pair() {
        Player p1 = new Player("Player1", cardsUtils.listOfCards(new String[]{"7C", "7H", "8D", "9D", "10D"}));
        Player p2 = new Player("Player2", cardsUtils.listOfCards(new String[]{"7D", "7S", "8H", "9H", "QH"}));
        Player p3 = new Player("Player3", cardsUtils.listOfCards(new String[]{"6C", "6H", "8C", "9S", "QS"}));
        Player p4 = new Player("Player4", cardsUtils.listOfCards(new String[]{"6D", "6S", "8S", "9C", "AC"}));

        List<Player> candidates = List.of(p1, p2, p3, p4);
        assertEquals(p2, cardsUtils.getWinner(candidates).poll());
    }

    @Test
    void Two_Pair() {
        Player p1 = new Player("Player1", cardsUtils.listOfCards(new String[]{"7C", "7H", "8C", "8H", "KD"}));
        Player p2 = new Player("Player2", cardsUtils.listOfCards(new String[]{"7D", "7S", "8D", "8S", "QH"}));
        Player p3 = new Player("Player3", cardsUtils.listOfCards(new String[]{"6C", "6H", "QC", "QH", "KS"}));
        Player p4 = new Player("Player4", cardsUtils.listOfCards(new String[]{"6D", "6S", "QD", "QS", "AC"}));

        List<Player> candidates = List.of(p1, p2, p3, p4);
        assertEquals(p4, cardsUtils.getWinner(candidates).poll());
    }

    @Test
    void TreeOfAKing() {
        Player p1 = new Player("Player1", cardsUtils.listOfCards(new String[]{"7D", "7S", "7H", "8H", "KD"}));
        Player p2 = new Player("Player2", cardsUtils.listOfCards(new String[]{"AD", "AS", "AH", "8S", "QH"}));
        Player p3 = new Player("Player3", cardsUtils.listOfCards(new String[]{"8D", "8S", "8H", "QD", "KS"}));
        Player p4 = new Player("Player4", cardsUtils.listOfCards(new String[]{"QD", "QS", "QH", "7S", "8C"}));

        List<Player> candidates = List.of(p1, p2, p3, p4);
        assertEquals(p2, cardsUtils.getWinner(candidates).poll());
    }

    @Test
    void Straight() {
        Player p1 = new Player("Player1", cardsUtils.listOfCards(new String[]{"7D", "8D", "9D", "10D", "JH"}));
        Player p2 = new Player("Player2", cardsUtils.listOfCards(new String[]{"8H", "9H", "10H", "JH", "QD"}));
        Player p3 = new Player("Player3", cardsUtils.listOfCards(new String[]{"10S", "JS", "QS", "KS", "AH"}));
        Player p4 = new Player("Player4", cardsUtils.listOfCards(new String[]{"9C", "10C", "JC", "QC", "KD"}));

        List<Player> candidates = List.of(p1, p2, p3, p4);
        assertEquals(p3, cardsUtils.getWinner(candidates).poll());
    }

    @Test
    void Flash() {
        Player p1 = new Player("Player1", cardsUtils.listOfCards(new String[]{"7D", "8D", "9D", "10D", "KD"}));
        Player p2 = new Player("Player2", cardsUtils.listOfCards(new String[]{"7H", "8H", "9H", "10H", "QH"}));
        Player p3 = new Player("Player3", cardsUtils.listOfCards(new String[]{"6S", "7S", "8S", "KS", "AS"}));
        Player p4 = new Player("Player4", cardsUtils.listOfCards(new String[]{"6C", "10C", "JC", "QC", "KC"}));

        List<Player> candidates = List.of(p1, p2, p3, p4);
        assertEquals(p3, cardsUtils.getWinner(candidates).poll());
    }

    @Test
    void FullHouse() {
        Player p1 = new Player("Player1", cardsUtils.listOfCards(new String[]{"7D", "7S", "8D", "8S", "8C"}));
        Player p2 = new Player("Player2", cardsUtils.listOfCards(new String[]{"9H", "9S", "10D", "10S", "10C"}));
        Player p3 = new Player("Player3", cardsUtils.listOfCards(new String[]{"6C", "6D", "QD", "QS", "QC"}));
        Player p4 = new Player("Player4", cardsUtils.listOfCards(new String[]{"6H", "6S", "KD", "KS", "KC"}));

        List<Player> candidates = List.of(p1, p2, p3, p4);
        assertEquals(p4, cardsUtils.getWinner(candidates).poll());
    }
}