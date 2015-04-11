package no.haavstr.videopoker;

/**
 * Deck.java
 * Created by haavstr on 07.02.15.
 */

import java.util.Collections;
import java.util.ArrayList;

public final class Deck extends ArrayList<Card> {


    @Override
    public int size() {
        return super.size();
    }

    /* Populates a new deck of cards. One of each value for each suit.
     * Note that they are initially not shuffled */
    Deck() {
        for (Card.Suit s: Card.Suit.values()) {
            for(Card.CardValue v : Card.CardValue.values()){
                this.add(new Card(s, v));
            }
        }
    }

    public void shuffle () {
        Collections.shuffle(this);
    }

    public void printDeck () {
        for(Card c: this) {
            System.out.print(c + " ");
        }
    }
}
