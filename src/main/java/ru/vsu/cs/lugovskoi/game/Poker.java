package ru.vsu.cs.lugovskoi.game;

import ru.vsu.cs.lugovskoi.bets.Bets;
import ru.vsu.cs.lugovskoi.bets.Chip;
import ru.vsu.cs.lugovskoi.cards.*;
import ru.vsu.cs.lugovskoi.players.Player;
import ru.vsu.cs.lugovskoi.utils.*;

import java.util.*;

public class Poker {
    private final Bets bets;
    private final Queue<Card> deck;
    private final Queue<Card> discardingCards;
    private final List<Player> players;
    private final int countRounds;

    public Poker(int countPlayers, int countRounds) {
        this(countPlayers, 5, 1900, countRounds);
    }

    private Poker(int countPlayers, int minBet, int capital, int countRounds) {
        this.countRounds = countRounds;
        bets = new Bets(minBet, capital / countRounds);
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
        cards.replaceAll(el -> deck.poll());

        for (int i = 0; i < 1e5; i++) {
            int curInd = i % cards.size();
            int randomInd = (int) (Math.random() * cards.size());
            Card tmp = cards.get(curInd);
            cards.set(curInd, cards.get(randomInd));
            cards.set(randomInd, tmp);
        }

        deck.addAll(cards);
    }




    private void updateDeck() {
        deck.addAll(discardingCards);
        shuffleDeck();
    }


    private int trade(Player player) {
        int bet = gameUtils.createBet(player, bets);
        if (bet < bets.getMinValue()) {
            players.remove(player);
        } else {
            bets.setBet(bet);
        }
        return bet;
    }



    public void startGame() {
        for (Player player : players) {
            for (int i = 0; i < 5; i++) {
                System.out.print(player.getCard(i) + " ");
            }
            System.out.println(trade(player));
        }

        finishGame();
    }

    private void finishGame() {
        System.out.println();
        for(Player player: players) {
            cardsUtils.changeCards(player, card -> {
                discardingCards.add(card);
                if (deck.size() == 0) {
                    updateDeck();
                }
                return deck.poll();
            });

            for (int i = 0; i < 5; i++) {
                System.out.print(player.getCard(i) + " ");
            }
            System.out.println(trade(player));
        }

        Queue<Player> candidates = gameUtils.getWinner(players);
        Player firstWinner = candidates.poll();
        System.out.println(firstWinner);
        while (!candidates.isEmpty()) {
            assert firstWinner != null;
            if (!(candidates.peek() == firstWinner)) break;
            System.out.println(candidates.poll());
        }
    }

    public Queue<Card> getDeck() {
        return deck;
    }
}














