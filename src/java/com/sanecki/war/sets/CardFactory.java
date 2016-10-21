package com.sanecki.war.sets;

import java.util.ArrayList;
import java.util.List;

/**
 * Card Factory which builds lists of <code>Card</code> objects
 * @see com.sanecki.war.sets.Card
 */
public class CardFactory {

    public List<Card> assembleCardsForASuit(int numberOfRanks) {
        List<Card> cards = new ArrayList<Card>();

        for(int i = 0; i < numberOfRanks; i++) {
            cards.add(new Card(i + 2));
        }

        return cards;
    }

    public List<Card> assembleCardsForADeck(int numberOfSuits, int numberOfRanks) {
        List<Card> cards = new ArrayList<Card>();
        for(int s = 0; s < numberOfSuits; s++) {
            List<Card> aSuitOfCards = this.assembleCardsForASuit(numberOfRanks);
            cards.addAll(aSuitOfCards);
        }
        return cards;
    }
}
