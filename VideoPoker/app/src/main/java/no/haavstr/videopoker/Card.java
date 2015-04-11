package no.haavstr.videopoker;

import java.lang.Override;
import java.lang.String;
import android.support.annotation.NonNull;

/**
 * Created by haavstr on 07.02.15.
 */

public class Card implements Comparable<Card>{
    private Suit suit;
    private CardValue value;

    Card(Suit suit, CardValue value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit () {
        return suit;
    }

    public CardValue getValue() {
        return value;
    }

    @Override
    public int compareTo(@NonNull Card c){
        Integer a = this.value.ordinal();
        Integer b = c.value.ordinal();
        return a.compareTo(b);
    }

    @Override public String toString (){
        return value.getCardFaceValue() + "_of_" + suit.getName();
    }

    enum Suit {
        SPADES, HEARTS, DIAMONDS, CLUBS;

        private String name;

        static {
            SPADES.name = "spades";
            HEARTS.name = "hearts";
            DIAMONDS.name = "diamonds";
            CLUBS.name = "clubs";
        }

        public String getName(){
            return name;
        }
    }

    public enum CardValue {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;

        private String cardFaceValue;

        static {
            ACE.cardFaceValue = "ace";
            TWO.cardFaceValue = "two";
            THREE.cardFaceValue = "three";
            FOUR.cardFaceValue = "four";
            FIVE.cardFaceValue = "five";
            SIX.cardFaceValue = "six";
            SEVEN.cardFaceValue = "seven";
            EIGHT.cardFaceValue = "eight";
            NINE.cardFaceValue = "nine";
            TEN.cardFaceValue = "ten";
            JACK.cardFaceValue = "jack";
            QUEEN.cardFaceValue = "queen";
            KING.cardFaceValue = "king";
        }

        public String getCardFaceValue() {
            return cardFaceValue;
        }


    }



}


