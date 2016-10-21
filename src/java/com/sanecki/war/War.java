package com.sanecki.war;

import com.google.common.base.Preconditions;
import com.sanecki.war.sets.Card;
import com.sanecki.war.sets.Deck;
import com.sanecki.war.sets.DeckImpl;
import com.sanecki.war.sets.EmptyDeckException;

import java.util.*;

import static com.sanecki.war.sets.Card.NULL_CARD;

/**
 * Driver Program that plays the sets to the rules of War
 */
public class War {

    Scanner reader = new Scanner(System.in);

    /**
     * Initializes War for Play
     *
     * Interacts with user for Deck details and Number of Players
     */
    public void init() {
        System.out.println("Create a new Game");

        int s;
        int r;
        int p;
        boolean isValid = false;
        do {
            System.out.println("Enter a number of suits: ");
            s = reader.nextInt();

            System.out.println("Enter a number of ranks: ");
            r = reader.nextInt();

            System.out.println("Enter a number of players: ");
            p = reader.nextInt();

            if(isValidNumberOfPlayers(s, r, p)) {
                isValid = true;
            } else {
                System.out.println("Invalid number players, please try again");
            }
        } while (!isValid);
        play(s, r, p);
    }

    boolean isValidNumberOfPlayers(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
        int cards = numberOfSuits * numberOfRanks;

        return numberOfPlayers > 1 && cards / numberOfPlayers >= 2;
    }

    List<List<Card>> constructPlayerHands(Deck deck, int numberOfPlayers) {
       Preconditions.checkNotNull(deck, "Deck must not be null");

       List<List<Card>> hands = new ArrayList<>();
       for(int p = 0; p < numberOfPlayers;p++) {
           hands.add(new ArrayList<>());
       }

       try {
           int cardCount = 0;
           while(true) {
               hands.get(cardCount % numberOfPlayers).add(deck.deal());
               cardCount++;
           }
       } catch (EmptyDeckException e) {
           // Do Nothing
       }
       return hands;
    }

    /**
     * Warning Tail end Recursive, not for the weak of heart
     * @param players
     * @param pile
     */
    void playCards(List<Player> players, List<Card> pile) {
        if(null == pile) {
            pile = new ArrayList<>();
        }

        if(1 == players.size() ) {
            players.get(0).winPile(pile);
            return;
        }

        List<Player> topPlayers = new ArrayList<>();
        Card highestCard = NULL_CARD;
        for(Player p: players) {

            if(!p.hasEmptyHand()) {
                Card card = p.takeCard();
                pile.add(card);
                if (card.equals(highestCard)) {
                    topPlayers.add(p);
                } else if (card.compareTo(highestCard) > 0) {
                    topPlayers = new ArrayList<>();
                    topPlayers.add(p);
                    highestCard = card;
                }
            }
        }
        // highest cards go to playCards, again...
        playCards(topPlayers, pile);
    }

    /**
     * Makes War, not peace
     * Plays out War with given deck with specified number of players.
     *
     * @param numberOfPlayers
     * @param deck
     * @return Players that played
     */
    List<Player> dealDeckAndPlayHands(int numberOfPlayers, Deck deck) {
        Preconditions.checkNotNull(deck, "Deck must not be null");

        List<List<Card>> hands = constructPlayerHands(deck, numberOfPlayers);

        List<Player> players = new ArrayList<>();

        for(int p = 0; p < numberOfPlayers; p++ ) {
            players.add(new Player(p, hands.get(p)));
        }

        boolean anyEmptyHands = false;
        while(!anyEmptyHands) {
            List<Card> pile = new ArrayList<>();

            playCards(players, pile);

            // ends with any players whom hands are empty
            for(Player p: players) {
                anyEmptyHands = p.hasEmptyHand();
            }
        }

        return players;
    }

    /**
     * Finds winners out of the list of <code>Players</code>
     * @param players
     * @return List of players that are the winners
     */
    List<Player> findWinners(List<Player> players) {
        List<Player> winners = new ArrayList<>();
        List<Player> sortedPlayers = new ArrayList<>(players);
        Collections.sort(sortedPlayers, Collections.reverseOrder());

        int highestCount = 0;
        for(Player p: sortedPlayers) {
            if(p.winnings() >= highestCount) {
                highestCount = p.winnings();
                winners.add(p);
            }
        }

        return winners;
    }

    /**
     * Plays of a War with specified number of players and deck details
     * Will report to console player number that was the winner of War Gambit
     * @param numberOfSuits
     * @param numberOfRanks
     * @param numberOfPlayers
     */
    public void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
        Preconditions.checkArgument(numberOfSuits > 0, "Suits must be greater than 0");
        Preconditions.checkArgument(numberOfRanks > 0, "Ranks must be greater than 0");
        Preconditions.checkArgument(numberOfPlayers > 1, "Players must be greater than 1");

        System.out.println("It is time for WAR!");

        Deck deck = new DeckImpl();
        deck.create(numberOfSuits, numberOfRanks);
        deck.shuffle();

        List<Player> players = dealDeckAndPlayHands(numberOfPlayers, deck);
        List<Player> winners = findWinners(players);
        System.out.println("Winners are...");
        System.out.println(winners);
    }

}
