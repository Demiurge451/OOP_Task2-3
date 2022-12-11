package ru.vsu.cs.lugovskoi.game;

import ru.vsu.cs.lugovskoi.bets.Bet;
import ru.vsu.cs.lugovskoi.cards.Card;
import ru.vsu.cs.lugovskoi.cards.CombinationComparator;
import ru.vsu.cs.lugovskoi.cards.Rank;
import ru.vsu.cs.lugovskoi.cards.Suit;
import ru.vsu.cs.lugovskoi.players.Player;
import ru.vsu.cs.lugovskoi.utils.*;

import java.util.*;
import java.util.function.UnaryOperator;

public class Poker {
    private final Bet bet;
    private Queue<Card> deck;
    private Queue<Card> discardingCards;
    private final List<Player> players;

    public Poker(int countPlayers) {
        this(countPlayers, 5, 1900);
    }

    private Poker(int countPlayers, int minBet, int capital) {
        bet = new Bet(minBet, capital);
        deck = createDeck();
        shuffleDeck();
        players = createPlayers(countPlayers);
        discardingCards = new LinkedList<>();
    }

    //TODO input
    private List<Player> createPlayers(int countPlayers) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < countPlayers; i++) {
            List<Card> cards = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                cards.add(deck.poll());
            }
            players.add(new Player("" + i, cards));
        }
        return players;
    }

    private Queue<Card> createDeck() {
        Queue<Card> deck = new LinkedList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
        return deck;
    }

    public void shuffleDeck() {
        List<Card> cards = Arrays.asList(new Card[deck.size()]);
        for (int i = 0; i < cards.size(); i++) {
            cards.set(i, deck.poll());
        }

        for (int i = 0; i < 1e5; i++) {
            int curInd = i % cards.size();
            int randomInd = (int) (Math.random() * cards.size());
            Card tmp = cards.get(curInd);
            cards.set(curInd, cards.get(randomInd));
            cards.set(randomInd, tmp);
        }

        deck.addAll(cards);
    }


    public void startGame() {
        for (Player player: players) {
            for (int i = 0; i < 5; i++) {
                System.out.print(player.getCard(i) + " ");
            }
            System.out.println();
        }

        finishGame();
    }

    private void updateDeck() {
        deck.addAll(discardingCards);
        shuffleDeck();
    }


    //TODO create
    private void nextTurn() {

    }

    private void finishGame() {
        Queue<Player> candidates = cardsUtils.getWinner(players);
        Player firstWinner = candidates.poll();
        System.out.println(firstWinner);
        while (!candidates.isEmpty() && candidates.peek().getCombination() == firstWinner.getCombination()){
            System.out.println(candidates.poll());
        }
    }

    public Queue<Card> getDeck() {
        return deck;
    }
}














