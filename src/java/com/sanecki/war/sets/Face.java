package com.sanecki.war.sets;

import java.util.Comparator;

/**
 * Card Faces
 */
public enum Face implements Comparator<Face> {

    ACE(13),
    TWO(1),
    THREE(2),
    FOUR(3),
    FIVE(4),
    SIX(5),
    SEVEN(6),
    EIGHT(7),
    NINE(8),
    TEN(9),
    JACK(10),
    KING(11),
    QUEEN(12);

    int rank;

    Face(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public int compare(Face o1, Face o2) {
        return o1.getRank() - o2.getRank();
    }
}
