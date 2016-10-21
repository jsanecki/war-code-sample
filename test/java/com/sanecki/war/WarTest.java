package com.sanecki.war;

import com.sanecki.war.sets.Card;
import com.sanecki.war.sets.Deck;
import com.sanecki.war.sets.DeckImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class WarTest {
    @Test
    public void shouldConstructHands() throws Exception {
        War war = new War();

        Deck deck = new DeckImpl();
        deck.create(4, 10);

        List<List<Card>> hands = war.constructHands(deck, 2);
        assertThat(hands.size(), is(equalTo(2)));
        List<Card> playOneHand = hands.get(0);
        assertThat(playOneHand.size(), is(equalTo(20)));
    }

    @Test
    public void shouldConstructHandsEvenWithOddPlayers() throws Exception {
        War war = new War();

        Deck deck = new DeckImpl();
        deck.create(4, 10);

        List<List<Card>> hands = war.constructHands(deck, 3);
        assertThat(hands.size(), is(equalTo(3)));
        List<Card> playerOneHand = hands.get(0);
        assertThat(playerOneHand.size(), is(equalTo(14)));
        List<Card> playerTwoHand = hands.get(1);
        assertThat(playerTwoHand.size(), is(equalTo(13)));
    }

    @Test
    public void shouldAwardPileToHighestCardInHand() {
        List<Player> players = new ArrayList<>();
        players.add(buildPlayer(10));
        players.add(buildPlayer(5));

        War war = new War();
        war.war(players, new ArrayList<>());

        assertThat(players.get(0).winningsPile, containsInAnyOrder(new Card(10), new Card(5)));
        assertThat(players.get(1).winningsPile.size(), is(equalTo(0)));
    }

    @Test
    public void shouldRebattleIfTieOnCard() {
        List<Player> players = new ArrayList<>();
        players.add(buildPlayer(10,4));
        players.add(buildPlayer(10,3));

        War war = new War();
        war.war(players, new ArrayList<>());

        assertThat(players.get(0).winningsPile, containsInAnyOrder(new Card(10), new Card(10), new Card(4), new Card(3)));
        assertThat(players.get(1).winningsPile.size(), is(equalTo(0)));
    }

    @Test
    public void shouldIgnorePlayerWithNoCardsInHand() {
        List<Player> players = new ArrayList<>();
        players.add(buildPlayer(10,5,3));
        players.add(buildPlayer(10));

        War war = new War();
        war.war(players, new ArrayList<>());

        assertThat(players.get(0).winningsPile.size(), is(equalTo(3)));
        assertThat(players.get(0).winningsPile, containsInAnyOrder(new Card(10), new Card(10), new Card(5)));
        assertThat(players.get(1).winningsPile.size(), is(equalTo(0)));
    }

    @Test
    public void shouldFindWinners() {
        List<Player> players = new ArrayList<>();
        players.add(buildWinningPlayer(10,5,3));
        players.add(buildWinningPlayer(10));

        War war = new War();
        List<Player> winners = war.findWinners(players);
        assertThat(winners.size(), is(equalTo(1)));
    }

    Player buildPlayer(Integer ... ranks) {
        List<Card> cards = new ArrayList<>();
        for(Integer r: ranks) {
            cards.add(new Card(r));
        }
        return new Player(cards);
    }

    Player buildWinningPlayer(Integer ... ranks) {
        List<Card> cards = new ArrayList<>();
        for(Integer r: ranks) {
            cards.add(new Card(r));
        }
        Player p = new Player(new ArrayList<>());
        p.winPile(cards);
        return p;
    }

}
