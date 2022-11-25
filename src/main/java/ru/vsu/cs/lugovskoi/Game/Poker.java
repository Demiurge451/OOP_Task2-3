package ru.vsu.cs.lugovskoi.Game;

import ru.vsu.cs.lugovskoi.Bets.Bet;
import ru.vsu.cs.lugovskoi.Cards.Card;
import ru.vsu.cs.lugovskoi.Cards.CombinationComparator;
import ru.vsu.cs.lugovskoi.Cards.Rank;
import ru.vsu.cs.lugovskoi.Cards.Suit;
import ru.vsu.cs.lugovskoi.Players.Player;

import java.util.*;

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
        players = createPlayers(countPlayers);
        deck = createDeck();
        shuffleDeck();
        discardingCards = new LinkedList<>();
    }

    //TODO input
    private List<Player> createPlayers(int countPlayers) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < countPlayers; i++) {
            players.add(new Player("" + i));
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
        Card[] cards = new Card[deck.size()];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = deck.poll();
        }

        for (int i = 0; i < 1e5; i++) {
            int curInd = i % cards.length;
            int randomInd = (int) (Math.random() * cards.length);
            Card tmp = cards[curInd];
            cards[curInd] = cards[randomInd];
            cards[randomInd] = tmp;
        }

        for (Card card : cards) {
            deck.add(card);
        }
    }


    public void startGame() {
        for (Player player: players) {
            for (int i = 0; i < 5; i++) {
                if (deck.size() == 0) {
                    updateDeck();
                }
                player.setCard(i, deck.poll());
            }
            player.createCombinations();
            for (int i = 0; i < 5; i++) {
                System.out.print(player.getCard(i) + " ");
            }
            System.out.println();
        }

        finishGame();
    }

    private void updateDeck() {
        for (Card card: discardingCards) {
            deck.add(card);
        }
        shuffleDeck();
    }


    //TODO create
    private void nextTurn() {

    }

    private void finishGame() {
        PriorityQueue<Player> winners = new PriorityQueue<>(new CombinationComparator().reversed());
        for(Player player: players) {
            winners.add(player);
            System.out.println(player.getCombination());
        }
        Player winner = winners.poll();
        System.out.println(winner.getName());
    }

    public Queue<Card> getDeck() {
        return deck;
    }
}














