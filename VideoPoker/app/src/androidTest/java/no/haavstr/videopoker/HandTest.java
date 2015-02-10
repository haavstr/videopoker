package no.haavstr.videopoker;


import android.test.suitebuilder.annotation.SmallTest;
import junit.framework.TestCase;

/**
 * Created by haavstr on 08.02.15.
 */
public class HandTest extends TestCase {

    Hand player = new Hand();
    Card aceOfSpades = new Card(Card.Suit.SPADES, Card.CardValue.ACE);
    Card kingOfSpades = new Card(Card.Suit.SPADES, Card.CardValue.KING);
    Card queenOfSpades = new Card(Card.Suit.SPADES, Card.CardValue.QUEEN);
    Card jackOfSpades = new Card(Card.Suit.SPADES, Card.CardValue.JACK);
    Card tenOfSpades = new Card(Card.Suit.SPADES, Card.CardValue.TEN);
    Card fiveOfSpades = new Card(Card.Suit.SPADES, Card.CardValue.FIVE);
    Card sixOfClubs = new Card(Card.Suit.CLUBS, Card.CardValue.SIX);
    Card fiveOfClubs = new Card(Card.Suit.CLUBS, Card.CardValue.FIVE);
    Card fourOfClubs = new Card(Card.Suit.CLUBS, Card.CardValue.FOUR);
    Card threeOfClubs = new Card(Card.Suit.CLUBS, Card.CardValue.THREE);
    Card twoOfClubs = new Card(Card.Suit.CLUBS, Card.CardValue.TWO);
    Card nineOfDiamonds = new Card(Card.Suit.DIAMONDS, Card.CardValue.NINE);
    Card eightOfDiamonds = new Card(Card.Suit.DIAMONDS, Card.CardValue.EIGHT);
    Card sevenOfDiamonds = new Card(Card.Suit.DIAMONDS, Card.CardValue.SEVEN);
    Card sixOfDiamonds = new Card(Card.Suit.DIAMONDS, Card.CardValue.SIX);
    Card fiveOfDiamonds = new Card(Card.Suit.DIAMONDS, Card.CardValue.FIVE);


    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }


    @SmallTest
    public void testCheckRoyalFlush() {
        player.hand[0] = aceOfSpades;
        player.hand[4] = kingOfSpades;
        player.hand[3] = queenOfSpades;
        player.hand[1] = tenOfSpades;
        player.hand[2] = jackOfSpades;
        assertEquals(player.checkWinnings(), Hand.Winner.ROYAL_FLUSH);
        player.hand[3] = fiveOfSpades;
        assertNotSame(player.checkWinnings(), Hand.Winner.ROYAL_FLUSH);
    }
    @SmallTest
    public void testCheckFlush() {
        player.hand[0] = aceOfSpades;
        player.hand[1] = tenOfSpades;
        player.hand[2] = jackOfSpades;
        player.hand[3] = queenOfSpades;
        player.hand[4] = fiveOfSpades;
        assertEquals(player.checkWinnings(), Hand.Winner.FLUSH);
        player.hand[2] = fiveOfClubs;
        assertNotSame(player.checkWinnings(), Hand.Winner.FLUSH);
    }

    @SmallTest
    public void testCheckStraight() {
        player.hand[0] = aceOfSpades;
        player.hand[1] = twoOfClubs;
        player.hand[2] = threeOfClubs;
        player.hand[4] = fourOfClubs;
        player.hand[3] = fiveOfSpades;
        assertEquals(player.checkWinnings(), Hand.Winner.STRAIGHT);
        assertNotSame(player.checkWinnings(), Hand.Winner.FLUSH);
        assertNotSame(player.checkWinnings(), Hand.Winner.ROYAL_FLUSH);
    }

    @SmallTest
    public void testCheckFullHouse() {
        player.hand[1] = fiveOfDiamonds;
        player.hand[4] = sixOfClubs;
        player.hand[2] = fiveOfClubs;
        player.hand[0] = sixOfDiamonds;
        player.hand[3] = fiveOfSpades;
        assertEquals(player.checkWinnings(), Hand.Winner.FULL_HOUSE);
        assertNotSame(player.checkWinnings(), Hand.Winner.STRAIGHT);
        assertNotSame(player.checkWinnings(), Hand.Winner.FLUSH);
        assertNotSame(player.checkWinnings(), Hand.Winner.ROYAL_FLUSH);
    }

    @SmallTest
    public void testCheckThreeOfAKind() {
        player.hand[1] = fiveOfDiamonds;
        player.hand[2] = fiveOfClubs;
        player.hand[0] = sixOfDiamonds;
        player.hand[3] = fiveOfSpades;
        player.hand[4] = nineOfDiamonds;
        assertEquals(player.checkWinnings(), Hand.Winner.THREE_OF_A_KIND);
        assertNotSame(player.checkWinnings(), Hand.Winner.FULL_HOUSE);
    }

    @SmallTest
    public void testCheckTwoPairs() {
        player.hand[1] = fiveOfDiamonds;
        player.hand[2] = fiveOfClubs;
        player.hand[0] = sixOfDiamonds;
        player.hand[4] = nineOfDiamonds;
        player.hand[3] = sixOfClubs;
        assertEquals(player.checkWinnings(), Hand.Winner.TWO_PAIR);
        assertNotSame(player.checkWinnings(), Hand.Winner.FULL_HOUSE);
    }

    @SmallTest
    public void testCheckPair() {
        player.hand[1] = fiveOfDiamonds;
        player.hand[2] = fiveOfClubs;
        player.hand[0] = sixOfDiamonds;
        player.hand[4] = nineOfDiamonds;
        player.hand[3] = eightOfDiamonds;
        assertEquals(player.checkWinnings(), Hand.Winner.PAIR);
        assertNotSame(player.checkWinnings(), Hand.Winner.TWO_PAIR);
    }



    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
    }

}
