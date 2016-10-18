package com.sanecki.war.sets;

/**
 * Card Suit
 */
public enum Suit {

    HEART("Hearts"),
    CLUBS("Clubs"),
    DIAMONDS("Diamonds"),
    SPADES("Spades");

    private String readableStr;

    Suit(String readableStr) {
        this.readableStr = readableStr;
    }

    public String toString() {
        return this.readableStr;
    }
}
