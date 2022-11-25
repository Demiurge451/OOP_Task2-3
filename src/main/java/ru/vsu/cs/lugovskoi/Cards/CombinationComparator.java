package ru.vsu.cs.lugovskoi.Cards;

import ru.vsu.cs.lugovskoi.Players.Player;

import java.util.*;

public class CombinationComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        if (o1.getCombination() != o2.getCombination()) {
            return Integer.compare(o1.getCombination().ordinal(), o2.getCombination().ordinal());
        }
        return compareDuplicate(o1, o2);
    }

    private class Duplicate implements Comparable<Duplicate> {
        int rank;
        int count;

        public Duplicate(int rank, int count) {
            this.rank = rank;
            this.count = count;
        }

        @Override
        public int compareTo(Duplicate o) {
            if (this.count != o.count) {
                return Integer.compare(this.count, o.count);
            }
            return Integer.compare(this.rank, o.rank);
        }
    }
    private int compareDuplicate(Player p1, Player p2) {
        TreeSet<Duplicate> pairs1 = findDuplicates(p1);
        TreeSet<Duplicate> pairs2 = findDuplicates(p2);
        TreeSet<Integer> kickers1 = findKickers(p1);
        TreeSet<Integer> kickers2 = findKickers(p2);
        while (pairs1.size() > 0 && pairs1.last().rank == pairs2.last().rank) {
            pairs1.pollLast();
            pairs2.pollLast();
        }
        if (pairs1.size() == 0) {
            while (kickers1.size() > 1 && kickers1.last().equals(kickers2.last())) {
                kickers1.pollLast();
                kickers2.pollLast();
            }
            return Integer.compare(kickers1.last(), kickers2.last());
        } else {
            return Integer.compare(pairs1.last().rank, pairs2.last().rank);
        }
    }
    private TreeSet<Integer> findKickers(Player player) {
        TreeSet<Integer> kickers = new TreeSet<>();
        for (Card card : player.getCards()) {
            int rank = card.getRank().ordinal();
            if (kickers.contains(rank)) {
                kickers.remove(rank);
            } else {
                kickers.add(rank);
            }
        }
        return kickers;
    }

    private TreeSet<Duplicate> findDuplicates(Player player) {
        TreeSet<Duplicate> duplicates = new TreeSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (Card card : player.getCards()) {
            int rank = card.getRank().ordinal();
            map.put(rank, map.getOrDefault(rank, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int rank = entry.getKey();
            int count = entry.getValue();
            if (count != 1) {
                duplicates.add(new Duplicate(rank, count));
            }
        }
        return duplicates;
    }
}
