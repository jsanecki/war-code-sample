package com.sanecki.war;

import com.sanecki.war.sets.Deck;
import com.sanecki.war.sets.DeckImpl;

import java.util.Scanner;

/**
 * Driver Program that plays the sets Game War
 */
public class War {

    Scanner reader = new Scanner(System.in);

    public void init() {
        System.out.println("Create a new Game");

        System.out.println("Enter a number of suits: ");
        int s = reader.nextInt();

        System.out.println("Enter a number of ranks: ");
        int r = reader.nextInt();

        System.out.println("Enter a number of players: ");
        int p = reader.nextInt();

        play(s, r, p);
    }

    /**
     * Makes War, not peace
     * Plays out War with given deck with specified number of players.
     *
     * @param numberOfPlayers
     * @param deck
     * @return Number of the Player that Won
     */
    int warWithDeck(int numberOfPlayers, Deck deck) {

        return 0;
    }

    /**
     * Plays of a War with specified number of players and deck details
     * Will report to console player number that was the winner of War Gambit
     * @param numberOfSuits
     * @param numberOfRanks
     * @param numberOfPlayers
     */
    public void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
        System.out.println("It is time for WAR!");

        Deck deck = new DeckImpl();
        deck.create(numberOfSuits, numberOfRanks);
        deck.shuffle();

        int winner = warWithDeck(numberOfPlayers, deck);
        System.out.println(String.format("The winner is Player %s", winner));
    }

}
