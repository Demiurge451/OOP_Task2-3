package ru.vsu.cs.lugovskoi.players;

public class Bets {
    private int minValue;
    private final int maxValue;
    private int bank;

    public Bets(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public boolean isBetCorrect(int bet) {
        return bet >= minValue && bet <= maxValue;
    }

    public void setBet(int bet) {
        if (isBetCorrect(bet)) {
            bank += bet;
            return;
        }
        throw new IllegalArgumentException();
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

    public int getMaxValue() {
        return maxValue;
    }
}
