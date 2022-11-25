package ru.vsu.cs.lugovskoi.Bets;

public class Bet {
    private int minValue;
    private final int maxValue;
    private int bank;

    public Bet(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public boolean isBetCorrect(int bet) {
        if (bet >= minValue && bet <= maxValue) {
            return true;
        }
        return false;
    }

    public boolean setBet(int bet) {
        if (isBetCorrect(bet)) {
            bank += bet;
            return true;
        }
        return false;
    }

    public int getBank() {
        return bank;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }
}
