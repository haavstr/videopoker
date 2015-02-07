package com.test.haavstr.testapp;

/**
 * Created by haavstr on 07.02.15.
 */


public class Card {
    private Suit suit;
    private CardValue value;

    Card(Suit suit, CardValue value) {
        this.suit = suit;
        this.value = value;
    }

    @Override public String toString {
        return suit.getLetter() + value.getCardFaceValue();
    }

    enum Suit {
        SPADES, HEARTS, DIAMONDS, CLUBS

        private char letter;

        static {
            SPADES.letter = 'S';
            HEARTS.letter = 'H';
            DIAMONDS.letter = 'D';
            CLUBS.letter = 'C';
        }

        public char getLetter(){
            return letter;
        }
    }

    enum CardValue {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING

        private String cardFaceValue;

        static {
            ACE.cardFaceValue = "A";
            TWO.cardFaceValue = "2";
            THREE.cardFaceValue = "3";
            FOUR.cardFaceValue = "4";
            FIVE.cardFaceValue = "5";
            SIX.cardFaceValue = "6";
            SEVEN.cardFaceValue = "7";
            EIGHT.cardFaceValue = "8";
            NINE.cardFaceValue = "9";
            TEN.cardFaceValue = "10";
            JACK.cardFaceValue = "J";
            QUEEN.cardFaceValue = "Q";
            KING.cardFaceValue = "K";
        }

        public String getCardFaceValue() {
            return cardFaceValue;
        }


    }



}


