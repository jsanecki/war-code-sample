package com.sanecki.war.sets;

/**
 * Playing Card
 */
public class Card {

    int rank;

    public Card(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Card)obj).getRank() == this.getRank();
    }

    @Override
    public String toString() {
        return "Card of Rank " + rank;
    }
}
