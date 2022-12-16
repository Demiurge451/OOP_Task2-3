package ru.vsu.cs.lugovskoi.bets;

public class Bets {
    private int minValue;
    private int maxValue;
    private int bank;

    public Bets(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    private boolean isBetCorrect(int bet) {
        if (bet >= minValue && bet <= maxValue) {
            return true;
        }
        return false;
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

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
}
