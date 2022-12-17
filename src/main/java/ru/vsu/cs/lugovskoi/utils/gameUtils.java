package ru.vsu.cs.lugovskoi.utils;

import ru.vsu.cs.lugovskoi.cards.Combination;
import ru.vsu.cs.lugovskoi.players.Bets;
import ru.vsu.cs.lugovskoi.players.Chip;
import ru.vsu.cs.lugovskoi.cards.CombinationComparator;
import ru.vsu.cs.lugovskoi.players.Player;

import java.util.*;

public final class gameUtils {
    private gameUtils() {
        throw new UnsupportedOperationException();
    }

    public static Queue<Player> getWinner(List<Player> players) {
        CombinationComparator comparator = new CombinationComparator();
        Queue<Player> winners = new LinkedList<>();
        PriorityQueue<Player> candidates = new PriorityQueue<>(comparator.reversed());

        candidates.addAll(players);

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
        int min = 0;
        if (wantRaise(player)) {
            min = bets.getMinValue();
        }
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

    public static boolean wantRaise(Player player) {
        Random random = new Random();
        Combination combination = player.getCombination();
        int max = 100;
        int min = combination.ordinal() * 10 + 10;
        int chance = random.nextInt(max - min + 1) + min;
        return chance >= 50;
    }
}
