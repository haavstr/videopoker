package no.haavstr.videopoker;

import java.util.Arrays;

/**
 * Created by haavstr on 08.04.15.
 */
public class GameControll {
    private Hand hand;
    private Deck deck;

    private int placeInDeck; //Indicates where in the deck the next card to be dealt is.

    private boolean changePosition[] = new boolean[5];

    GameControll() {
        placeInDeck = 0;
        deck = new Deck();
        deck.shuffle();
    }

    void newRound(){
        for(int i = 1; i <= 5 ; i++) {
            hand.hand[i] = deck.get(placeInDeck);
            placeInDeck++;
        }
    }

    void changeCards(){
        for(int i = 1; i <= 5 ; i++) {
            if(changePosition[i]){
                hand.hand[i] = deck.get(placeInDeck);
            }
        }
    }

    void resetChange(){
        Arrays.fill(changePosition, false);
    }


}
