package com.sanecki.war.sets;

/**
 * Playing Card
 */
public class Card {

    Suit suit;
    Face face;

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }
}
