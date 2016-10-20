package com.sanecki.war.sets;

/**
 * Exception that Deck is Empty, and requested mutation on the deck failed
 */
public class EmptyDeckException extends Exception {
    public EmptyDeckException() {
        super("Deck is Empty");
    }
}
