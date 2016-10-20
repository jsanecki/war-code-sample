package com.sanecki.war.sets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 10/19/2016.
 */
public class CardFactory {

    public List<Card> assembleCardsForASuit(int numberOfRanks) {
        List<Card> cards = new ArrayList<Card>();

        for(int i = 0; i < numberOfRanks; i++) {
            cards.add(new Card(i + 2));
        }

        return cards;
    }
}
