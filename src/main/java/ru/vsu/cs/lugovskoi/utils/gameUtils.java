package ru.vsu.cs.lugovskoi.utils;

import ru.vsu.cs.lugovskoi.bets.Bets;
import ru.vsu.cs.lugovskoi.bets.Chip;
import ru.vsu.cs.lugovskoi.cards.CombinationComparator;
import ru.vsu.cs.lugovskoi.players.Player;

import java.util.*;

public final class gameUtils {
    private gameUtils() {
        throw new UnsupportedOperationException();
    }

    //TODO rewrite sout
    public static Queue<Player> getWinner(List<Player> players) {
        CombinationComparator comparator = new CombinationComparator();
        Queue<Player> winners = new LinkedList<>();
        PriorityQueue<Player> candidates = new PriorityQueue<>(comparator.reversed());

        for (Player player : players) {
            candidates.add(player);
            System.out.println(player.getCombination());
        }

        winners.add(candidates.poll());
        while (!candidates.isEmpty()) {
            assert winners.peek() != null;
            if (comparator.compare(winners.peek(), candidates.peek()) != 0) break;
            winners.add(candidates.poll());
        }
        return winners;
    }

    public static int createBet(Player player, Bets bets) {
        int max = bets.getMaxValue();
        int min = (max * (player.getCombination().ordinal() + 1) * 10) / 100;
        min = Math.min(min, max);

        int potentialBet = new Random().nextInt(max - min + 1) + min;
        int bet = 0;
        for (Chip chip : player.getChips().keySet()) {
            if (bet >= potentialBet) {
                break;
            }
            int sum = player.takeChip(chip, potentialBet - bet);
            bet += sum;
        }
        return bet;
    }
}
