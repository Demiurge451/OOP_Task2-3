package ru.vsu.cs.lugovskoi.utils;

public record Duplicate(int rank, int count) implements Comparable<Duplicate> {

    @Override
    public int compareTo(Duplicate o) {
        if (this.count != o.count) {
            return Integer.compare(this.count, o.count);
        }
        return Integer.compare(this.rank, o.rank);
    }
}
