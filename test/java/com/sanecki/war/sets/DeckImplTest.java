package com.sanecki.war.sets;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

public class DeckImplTest {
    @Test
    public void ShouldCreateDeckWithOneCard() throws Exception {
        DeckImpl deck = new DeckImpl();

        deck.create(1, 1);
        assertThat(deck.cardsRemaining(), equalTo(1));
        assertThat(deck.getFirstCard(), equalTo(new Card(2)));
    }

    @Test
    public void ShouldCreateDeckWithOneCardPerSuit() throws Exception {
        DeckImpl deck = new DeckImpl();

        Card twoCard = new Card(2);

        deck.create(4, 1);
        assertThat(deck.cardsRemaining(), equalTo(4));
        assertThat(deck.deal(), equalTo(twoCard));
        assertThat(deck.deal(), equalTo(twoCard));
        assertThat(deck.deal(), equalTo(twoCard));
        assertThat(deck.deal(), equalTo(twoCard));
    }

    @Test
    public void ShouldCreateDeckWithMultipleRanks() throws Exception {
        DeckImpl deck = new DeckImpl();

        deck.create(1, 13);
        assertThat(deck.cardsRemaining(), equalTo(13));
        assertThat(deck.getFirstCard(), equalTo(new Card(2)));
        assertThat(deck.getLastCard(), equalTo(new Card(14)));
    }

    @Test
    public void ShouldShuffleCards() throws Exception {
        Random fixedRandomizer = new Random(101L);
        DeckImpl deck = new DeckImpl(fixedRandomizer);

        deck.create(1, 10);
        deck.shuffle();
        assertThat(deck.cardsRemaining(), equalTo(10));
        assertThat(deck.getFirstCard(), not(equalTo(new Card(2))));
        assertThat(deck.getLastCard(), not(equalTo(new Card(11))));
    }

    @Test
    public void ShouldDepleteDeckOnDeal() throws Exception {
        DeckImpl deck = new DeckImpl();

        deck.create(1, 10);
        assertThat(deck.cardsRemaining(), equalTo(10));
        deck.deal();
        assertThat(deck.cardsRemaining(), is(equalTo(9)));
    }

    @Test(expected = EmptyDeckException.class)
    public void ShouldThrownExceptionOnEmptyDeck() throws Exception {
        DeckImpl deck = new DeckImpl();
        deck.deal();
    }

}
