package ru.vsu.cs.lugovskoi.cards;

import ru.vsu.cs.lugovskoi.players.Player;
import ru.vsu.cs.lugovskoi.utils.*;

import java.util.*;

public class CombinationComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        if (o1.getCombination() != o2.getCombination()) {
            return Integer.compare(o1.getCombination().ordinal(), o2.getCombination().ordinal());
        }
        return compareDuplicate(o1, o2);
    }


    private int compareDuplicate(Player p1, Player p2) {
        TreeSet<Duplicate> pairs1 = cardsUtils.findDuplicates(p1);
        TreeSet<Duplicate> pairs2 = cardsUtils.findDuplicates(p2);
        TreeSet<Integer> kickers1 = cardsUtils.findKickers(p1);
        TreeSet<Integer> kickers2 = cardsUtils.findKickers(p2);
        while (pairs1.size() > 0 && pairs1.last().rank() == pairs2.last().rank()) {
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
            return Integer.compare(pairs1.last().rank(), pairs2.last().rank());
        }
    }
}
