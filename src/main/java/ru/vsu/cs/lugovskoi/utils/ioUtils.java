package ru.vsu.cs.lugovskoi.utils;

import ru.vsu.cs.lugovskoi.cards.Rank;
import ru.vsu.cs.lugovskoi.cards.Suit;
import ru.vsu.cs.lugovskoi.players.Player;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


public final class ioUtils {
    private static final PrintWriter out = new PrintWriter(System.out);

    private ioUtils() {
        throw new UnsupportedOperationException();
    }

    public static Rank getRankFromString(String s) {
        if (s.length() == 2 || s.startsWith("10")) {
            for (Rank rank : Rank.values()) {
                if (rank.getName().charAt(0) == s.charAt(0)) {
                    return rank;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    public static Suit getSuitFromString(String s) {
        int ind = s.length() - 1;
        if (s.length() == 2 || s.startsWith("10")) {
            for (Suit suit : Suit.values()) {
                if (suit.getCharSuit().equals(String.valueOf(s.charAt(ind)))) {
                    return suit;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    public static void printInformationAboutPlayer(Player player) {
        out.printf("%S, combination: %S ", player.getName(), player.getCombination());
        for (int i = 0; i < player.getCards().size(); i++) {
            out.printf(player.getCard(i) + " ");
        }
        out.println();
    }

    public static void printCurRoundInfo(List<Player> players, int round) {
        if (round != 0) {
            out.printf("Round %d\n", round);
        }
        for (Player player : players) {
            printInformationAboutPlayer(player);
        }
        out.println();
    }

    public static void printCurTradeInfo(Map<Player, Integer> playersBets) {
        out.print("Bets of players:\n");
        for (Map.Entry<Player, Integer> entry : playersBets.entrySet()) {
            out.printf("%s, bet = %d\n", entry.getKey().getName(), entry.getValue());
        }
        out.println();
    }

    public static void printInfoAboutWinners(List<Player> winners, int bank) {
        int curBank = bank / winners.size();
        out.printf("Winners:\n");
        printCurRoundInfo(winners, 0);
        out.printf("Bank for every winners: %d\n", bank / winners.size());

    }

    public static void close() {
        out.close();
    }
}
