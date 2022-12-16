package ru.vsu.cs.lugovskoi.game;

import ru.vsu.cs.lugovskoi.bets.Bet;
import ru.vsu.cs.lugovskoi.cards.Card;
import ru.vsu.cs.lugovskoi.cards.Combination;
import ru.vsu.cs.lugovskoi.cards.Rank;
import ru.vsu.cs.lugovskoi.cards.Suit;
import ru.vsu.cs.lugovskoi.players.Player;
import ru.vsu.cs.lugovskoi.utils.*;

import java.util.*;

public class Poker {
    private final Bet bet;
    private final Queue<Card> deck;
    private final Queue<Card> discardingCards;
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


    public void startGame() {
        for (Player player : players) {
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
    private void trade() {

    }

    private void changeCards(Player player) {
        TreeSet<Integer> kickers = cardsUtils.findKickers(player);
        TreeSet<Duplicate> duplicates = cardsUtils.findDuplicates(player);
        int cardToChange = (int) (Math.random() * (kickers.size() + 1));
        Combination combination = player.getCombination();
        if (!duplicates.isEmpty() || combination == Combination.HIGH_CARD) {
            for (int i = 0; i < player.getCards().size(); i++) {
                Card card = player.getCard(i);
                if (kickers.contains(card.getRank()) && cardToChange > 0) {
                    if ((combination == Combination.THREE_OF_A_KING || combination == Combination.FOUR_OF_A_KING)
                            && card.getRank() == Rank.ACE) {
                        continue;
                    }
                    player.setCard(i, replaceCard(card));
                    cardToChange--;
                }
            }
        }
    }

    private Card replaceCard(Card card) {
        discardingCards.add(card);
        if (deck.size() == 0) {
            updateDeck();
        }
        return deck.poll();
    }

    private void decomposeCombination(Player player) {

    }

    private void finishGame() {
        for(Player player: players) {
            changeCards(player);
            for (int i = 0; i < 5; i++) {
                System.out.print(player.getCard(i) + " ");
            }
            System.out.println();
        }

        Queue<Player> candidates = cardsUtils.getWinner(players);
        Player firstWinner = candidates.poll();
        System.out.println(firstWinner);
        while (!candidates.isEmpty()) {
            assert firstWinner != null;
            if (!(candidates.peek().getCombination() == firstWinner.getCombination())) break;
            System.out.println(candidates.poll());
        }
    }

    public Queue<Card> getDeck() {
        return deck;
    }
}














