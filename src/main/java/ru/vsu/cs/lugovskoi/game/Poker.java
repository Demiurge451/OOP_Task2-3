package ru.vsu.cs.lugovskoi.game;

import ru.vsu.cs.lugovskoi.players.Bets;
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
        if (countPlayers > 10 || countPlayers < 0) {
            throw new IllegalArgumentException("countPlayers must be <= 10 && > 0");
        }
    }

    private Poker(int countPlayers, int minBet, int capital, int countRounds) {
        this.countRounds = countRounds + 1;
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
            players.add(new Player("Player " + (i + 1), cards));
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

    private void trade(List<Player> players) {
        List<Player> toRemove = new ArrayList<>();
        Map<Player, Integer> playersBets = new LinkedHashMap<>();
        for (Player player : players) {
            int bet = gameUtils.createBet(player, bets);
            if (!bets.isBetCorrect(bet) && toRemove.size() + 1 < players.size() ) {
                toRemove.add(player);
            } else if (bets.isBetCorrect(bet)){
                playersBets.put(player, bet);
                bets.setBet(bet);
                bets.setMinValue(Math.max(bet, bets.getMinValue()));
            }
        }
        players.removeAll(toRemove);
        ioUtils.printCurTradeInfo(playersBets);
    }

    public void startGame() {
        ioUtils.printCurRoundInfo(players, 0);
        continueGame();
        ioUtils.close();
    }

    private void changeCards(Player player) {
        cardsUtils.changeCards(player, card -> {
            discardingCards.add(card);
            if (deck.size() == 0) {
                updateDeck();
            }
            return deck.poll();
        });
    }

    public void continueGame() {
        for (int i = 1; i < countRounds; i++) {
            for (Player player : players) {
                changeCards(player);
            }
            trade(players);
            ioUtils.printCurRoundInfo(players, i);
            if (players.size() == 1) {
                break;
            }
        }
        finishGame();
    }

    private void finishGame() {
        Queue<Player> candidates = gameUtils.getWinner(players);
        List<Player> winners = new ArrayList<>();
        Player firstWinner = candidates.poll();
        winners.add(firstWinner);
        while (!candidates.isEmpty()) {
            assert firstWinner != null;
            if (!(candidates.peek() == firstWinner)) break;
            winners.add(candidates.poll());
        }
        ioUtils.printInfoAboutWinners(winners, bets.getBank());
    }
}














