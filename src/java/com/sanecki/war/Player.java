package com.sanecki.war;

import com.sanecki.war.sets.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * War Player
 */
public class Player implements Comparable<Player> {

    int id = 0;
    List<Card> winningsPile = new ArrayList<>();
    List<Card> hand = new ArrayList<>();

    public Player(int id, List<Card> cards) {
        this(cards);
        this.id = id;
    }

    public Player(List<Card> cards) {
        this.hand = cards;
    }

    public boolean hasEmptyHand() {
        return hand.isEmpty();
    }

    /**
     * Add Cards to Player's win Pile
     * @param pile
     */
    public void winPile(List<Card> pile) {
        winningsPile.addAll(pile);
    }

    /**
     * Takes a card from the Player's hand
     * @return Removed CArd from the Player's hand
     */
    public Card takeCard() {
        return hand.remove(0);
    }


    @Override
    public String toString() {
        return String.format("Player #%s with %s cards", id, winnings());
    }

    @Override
    public int compareTo(Player o) {
        return this.winnings() - o.winnings();
    }

    /**
     * The Amount of Cards this Player has won.
     * @return The Count of Cards
     */
    public int winnings() {
        return winningsPile.size();
    }
}
