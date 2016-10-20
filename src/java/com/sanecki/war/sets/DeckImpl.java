package com.sanecki.war.sets;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Concert Class for Deck
 */
public class DeckImpl implements Deck {

    Random randomizer;

    List<Card> cards;

    CardFactory cardFactory;

    /**
     * Constructor for Controlled Seed Randomizer
     * @param random
     */
    @VisibleForTesting
    DeckImpl(Random random) {
        this.cards = new ArrayList<>();
        this.cardFactory = new CardFactory();
        this.randomizer = random;
    }

    public DeckImpl() {
        this(new Random(System.nanoTime()));

    }

    @Override
    public void create(int numberOfSuits, int numberOfRanks) {
        for(int s = 0; s < numberOfSuits; s++) {
            List<Card> aSuitOfCards = cardFactory.assembleCardsForASuit(numberOfRanks);
            cards.addAll(aSuitOfCards);
        }
    }

    @Override
    public void shuffle() {
        if(!cards.isEmpty()) {
            Collections.shuffle(cards, randomizer);
        }
    }

    @Override
    public Card deal() throws EmptyDeckException {
        if(!cards.isEmpty()) {
            return cards.remove(0);
        } else {
            throw new EmptyDeckException();
        }
    }

    @VisibleForTesting
    int cardsRemaining() {
        return cards.size();
    }

    @VisibleForTesting
    Card getFirstCard() {
        return cards.get(0);
    }

    @VisibleForTesting
    Card getLastCard() {
        return cards.get(cardsRemaining() - 1);
    }
}
