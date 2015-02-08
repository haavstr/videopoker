package no.haavstr.videopoker;

/**
 * Created by haavstr on 07.02.15.
 *  */
public class Hand {
    Card hand[];

    public Hand() {
        hand = new Card [5];
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



    enum Winner {
        ROYAL_FLUSH, STRAIGHT_FLUSH, FOUR_OF_A_KIND, FULL_HOUSE, FLUSH, STRAIGHT, THREE_OF_A_KIND, TWO_PAIR, PAIR
    }
}
