package no.haavstr.videopoker;

import java.util.Random;

/**
 * Deck.java
 * Created by haavstr on 07.02.15.
 */
public class Deck {
    Card deck[];

    Deck() {
        deck = new Card[52];
        int i = 0;
        for (Card.Suit s: Card.Suit.values()) {
            for(Card.CardValue v : Card.CardValue.values()){
                deck[i] = new Card(s, v);
                i++;
            }
        }
    }

    public void shuffle () {
        Random rand = new Random();
        Card temp;

        for(int i = 0; i < deck.length; i++) {
            int index = rand.nextInt(i);
            temp = deck[i];
            deck [i] = deck [index];
            deck [index] = temp;
        }

    }

    public void printDeck () {

        for(Card c: deck) {
            System.out.print(c + " ");
        }
    }

}
