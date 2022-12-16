package ru.vsu.cs.lugovskoi.utils;

public class Duplicate implements Comparable<Duplicate> {
    private final int rank;
    private final int count;

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

    public int getRank() {
        return rank;
    }

    public int getCount() {
        return count;
    }
}
