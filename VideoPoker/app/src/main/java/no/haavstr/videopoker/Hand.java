package no.haavstr.videopoker;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by haavstr on 07.02.15.
 *  */
public class Hand {
    Card hand[];

    Card.CardValue cardValues[];

    public Hand() {
        hand = new Card [5];
        cardValues = Card.CardValue.values();
    }

    public Card getFirst () {
        return hand[0];
    }
    public Card getSecond (){
        return hand[1];
    }
    public Card getThird ()
    {
        return hand[2];
    }
    public Card getForth ()
    {
        return hand[3];
    }
    public Card getFifth ()
    {
        return hand[4];
    }

    /**
     * Clears all the pointers from the hand
     */
    void clearHand () {
        for(int i = 0; i < hand.length; i++) {
            hand [i] = null;
        }
    }

     Winner checkWinnings () {
        if (false){
            return Winner.ROYAL_FLUSH;
        }
        return null;
    }

    /*
     * All the below methods must only be called after the cards are sorted by card value
     */

    private boolean isFlush () {
        for(int i = 0; i < hand.length - 1; i++) {
            if(hand[i].Suit != hand[i+1].Suit) {
                return false;
            }
        }
        return true;
    }

    private boolean isStraight() {
        return false;
    }

    private boolean isFourOfAKind () {
        if(hand[0].CardValue == hand[1].CardValue) {
            return (hand[0].CardValue == hand[1].CardValue &&
                    hand[1].CardValue == hand[2].CardValue &&
                    hand[2].CardValue == hand[3].CardValue);
        } else {
            return (hand[1].CardValue == hand[2].CardValue &&
                    hand[2].CardValue == hand[3].CardValue &&
                    hand[3].CardValue == hand[4].CardValue);
        }
    }

    private boolean isFullHouse () {
        if(hand[0].CardValue == hand[1].CardValue)
    }

    enum Winner {
        ROYAL_FLUSH, STRAIGHT_FLUSH, FOUR_OF_A_KIND, FULL_HOUSE, FLUSH, STRAIGHT, THREE_OF_A_KIND, TWO_PAIR, PAIR
    }
}
