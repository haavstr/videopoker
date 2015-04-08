package no.haavstr.videopoker;

import java.util.ArrayList;
import java.util.Arrays;
import no.haavstr.videopoker.Card.Suit;
import no.haavstr.videopoker.Card.CardValue;
/**
 * Created by haavstr on 07.02.15.
 *  */
public class Hand {
    Card hand[];

    CardValue cardValues[];

    public Hand() {
        hand = new Card [5];
        cardValues = CardValue.values();
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

     public Winner checkWinnings () {
        sortHand();
         //TODO
        if (isRoyalFlush()){
            return Winner.ROYAL_FLUSH;
        } else if (isFlush() && isStraight()) {
           return Winner.STRAIGHT_FLUSH;
        } else if (isFourOfAKind()) {
             return Winner.FOUR_OF_A_KIND;
        } else if (isFullHouse()) {
            return Winner.FULL_HOUSE;
        } else if (isFlush()) {
            return Winner.FLUSH;
        } else if (isStraight()) {
            return Winner.STRAIGHT;
        } else if (isThreeOfAKind()) {
            return Winner.THREE_OF_A_KIND;
        } else if (isTwoPairs()) {
            return Winner.TWO_PAIR;
        } else if (isPair()) {
            return Winner.PAIR;
        } else {
            return null;
        }
    }





    void sortHand() {
        Arrays.sort(hand);
    }

    /*
     * All the below methods must only be called after the cards are sorted by card value
     */

    private boolean isFlush () {
        for(int i = 0; i < hand.length - 1; i++) {
            if (hand[i].getSuit() != hand[i+1].getSuit()) {
                return false;
            }
        }
        return true;
    }

    private boolean isStraight() {
        /* Check for special case of Ace to five straight, which is the only straight with ace as bottomcard */
        if(hand[4].getValue() == CardValue.ACE &&
                hand[0].getValue() == CardValue.TWO &&
                hand[1].getValue() == CardValue.THREE &&
                hand[2].getValue() == CardValue.FOUR &&
                hand[3].getValue() == CardValue.FIVE) {
            return true;
        } else {
            for (int i = 0; i < 4; i++) {
                if (hand[i].getValue().ordinal() != hand[i + 1].getValue().ordinal() -1) {
                    return false;
                }
            }
            return true;
        }
    }

    private boolean isRoyalFlush () {
        return isFlush() &&
                hand[0].getValue() == CardValue.TEN &&
                hand[1].getValue() == CardValue.JACK &&
                hand[2].getValue() == CardValue.QUEEN &&
                hand[3].getValue() == CardValue.KING &&
                hand[4].getValue() == CardValue.ACE;
    }

    private boolean isFourOfAKind () {
        if(hand[0].getValue() == hand[1].getValue()) {
            return (hand[0].getValue() == hand[1].getValue() &&
                    hand[1].getValue() == hand[2].getValue() &&
                    hand[2].getValue() == hand[3].getValue());
        } else {
            return (hand[1].getValue() == hand[2].getValue() &&
                    hand[2].getValue() == hand[3].getValue() &&
                    hand[3].getValue() == hand[4].getValue());
        }
    }

    private boolean isFullHouse () {
        if(hand[0].getValue() == hand[1].getValue()) {
            if(hand[2].getValue() == hand[3].getValue() &&
               hand[3].getValue() == hand[4].getValue()) {
                return true;
            } else if (hand[1].getValue() == hand[2].getValue() &&
                       hand[3].getValue() == hand[4].getValue()) {
                return true;
            }
        }
        return false;
    }

    private boolean isThreeOfAKind () {
        if (hand[0].getValue() == hand[1].getValue() &&
                hand[1].getValue() == hand[2].getValue()){
            return true;
        } else if (hand[1].getValue() == hand[2].getValue() &&
                hand[2].getValue() == hand[3].getValue()){
            return true;
        } else if (hand[2].getValue() == hand[3].getValue() &&
                hand[3].getValue() == hand[4].getValue()){
            return true;
        }
        return false;
    }

    private boolean isTwoPairs() {
        if (hand[0].getValue() == hand[1].getValue() &&
            hand[2].getValue() == hand[3].getValue()) {
            return true;
        } else if (hand[1].getValue() == hand[2].getValue() &&
                   hand[3].getValue() == hand[4].getValue()) {
            return true;
        } else if (hand[0].getValue() == hand[1].getValue() &&
                   hand[3].getValue() == hand[4].getValue()) {
            return true;
        }
        return false;
    }

    private boolean isPair () {
        if (hand[0].getValue() == hand[1].getValue()) {
            return true;
        } else if (hand[1].getValue() == hand[2].getValue()) {
            return true;
        } else if (hand[2].getValue() == hand[3].getValue()) {
            return true;
        } else if (hand[3].getValue() == hand[4].getValue()) {
            return true;
        }
        return false;
    }

    enum Winner {
        ROYAL_FLUSH, STRAIGHT_FLUSH, FOUR_OF_A_KIND, FULL_HOUSE, FLUSH, STRAIGHT, THREE_OF_A_KIND, TWO_PAIR, PAIR
    }
}
