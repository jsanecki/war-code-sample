package com.sanecki.war.sets;

import java.util.Comparator;

/**
 * Playing Card
 */
public class Card implements Comparator<Card>, Comparable<Card> {

    public static Card NULL_CARD = new Card(0);

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
    public int compare(Card o1, Card o2) {
        return o1.getRank() - o2.getRank();
    }

    @Override
    public boolean equals(Object obj) {
        return ((Card)obj).getRank() == this.getRank();
    }

    @Override
    public String toString() {
        return "Card of Rank " + rank;
    }

    @Override
    public int compareTo(Card o) {
        return this.rank - o.rank;
    }
}
