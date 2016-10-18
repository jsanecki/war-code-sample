package com.sanecki.war.sets;

/**
 * Deck Interface
 */
public interface Deck {

    void create (int numberOfSuits, int numberOfRanks);

    void shuffle();

    Card deal();
}
