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
    Card nineOfSpades = new Card(Card.Suit.SPADES, Card.CardValue.NINE);
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
        assertEquals(player.checkWinnings(), Winner.ROYAL_FLUSH);
        assertNotSame(player.checkWinnings(), Winner.STRAIGHT_FLUSH);
        assertNotSame(player.checkWinnings(), Winner.STRAIGHT);
        player.hand[3] = fiveOfSpades;
        assertNotSame(player.checkWinnings(), Winner.ROYAL_FLUSH);
    }
    @SmallTest
    public void testCheckFlush() {
        player.hand[0] = aceOfSpades;
        player.hand[1] = tenOfSpades;
        player.hand[2] = jackOfSpades;
        player.hand[3] = queenOfSpades;
        player.hand[4] = fiveOfSpades;
        assertEquals(player.checkWinnings(), Winner.FLUSH);
        player.hand[2] = fiveOfClubs;
        assertNotSame(player.checkWinnings(), Winner.FLUSH);
    }

    @SmallTest
    public void testCheckStraightFlush() {
        player.hand[0] = kingOfSpades;
        player.hand[1] = tenOfSpades;
        player.hand[2] = jackOfSpades;
        player.hand[3] = queenOfSpades;
        player.hand[4] = nineOfSpades;
        assertEquals(player.checkWinnings(), Winner.STRAIGHT_FLUSH);
        player.hand[2] = fiveOfClubs;
        assertNotSame(player.checkWinnings(), Winner.FLUSH);
    }


    @SmallTest
    public void testCheckStraight() {
        player.hand[3] = aceOfSpades;
        player.hand[4] = twoOfClubs;
        player.hand[2] = threeOfClubs;
        player.hand[1] = fourOfClubs;
        player.hand[0] = fiveOfDiamonds;
        assertEquals(player.checkWinnings(), Winner.STRAIGHT);
        assertNotSame(player.checkWinnings(), Winner.FLUSH);
        assertNotSame(player.checkWinnings(), Winner.ROYAL_FLUSH);
        player.hand[4] = queenOfSpades;
        assertNotSame(player.checkWinnings(), Winner.STRAIGHT);
    }

    @SmallTest
    public void testCheckFullHouse() {
        player.hand[1] = fiveOfDiamonds;
        player.hand[4] = sixOfClubs;
        player.hand[2] = fiveOfClubs;
        player.hand[0] = sixOfDiamonds;
        player.hand[3] = fiveOfSpades;
        assertEquals(player.checkWinnings(), Winner.FULL_HOUSE);
        assertNotSame(player.checkWinnings(), Winner.STRAIGHT);
        assertNotSame(player.checkWinnings(), Winner.FLUSH);
        assertNotSame(player.checkWinnings(), Winner.ROYAL_FLUSH);
    }

    @SmallTest
    public void testCheckThreeOfAKind() {
        player.hand[1] = fiveOfDiamonds;
        player.hand[2] = fiveOfClubs;
        player.hand[0] = sixOfDiamonds;
        player.hand[3] = fiveOfSpades;
        player.hand[4] = nineOfDiamonds;
        assertEquals(player.checkWinnings(), Winner.THREE_OF_A_KIND);
        assertNotSame(player.checkWinnings(), Winner.FULL_HOUSE);
    }

    @SmallTest
    public void testCheckTwoPairs() {
        player.hand[1] = fiveOfDiamonds;
        player.hand[2] = fiveOfClubs;
        player.hand[0] = sixOfDiamonds;
        player.hand[4] = nineOfDiamonds;
        player.hand[3] = sixOfClubs;
        assertEquals(player.checkWinnings(), Winner.TWO_PAIR);
        assertNotSame(player.checkWinnings(), Winner.FULL_HOUSE);
    }

    @SmallTest
    public void testCheckPair() {
        player.hand[1] = fiveOfDiamonds;
        player.hand[2] = fiveOfClubs;
        player.hand[0] = sixOfDiamonds;
        player.hand[4] = nineOfDiamonds;
        player.hand[3] = eightOfDiamonds;
        assertEquals(player.checkWinnings(), Winner.PAIR);
        assertNotSame(player.checkWinnings(), Winner.TWO_PAIR);
        assertNotSame(player.checkWinnings(), Winner.STRAIGHT);
    }

    @SmallTest
    public void testCheckNothing() {
        player.hand[0] = sixOfDiamonds;
        player.hand[1] = queenOfSpades;
        player.hand[2] = fiveOfClubs;
        player.hand[3] = eightOfDiamonds;
        player.hand[4] = nineOfDiamonds;
        assertNull(player.checkWinnings());

        player.hand[0] = sixOfDiamonds;
        player.hand[1] = queenOfSpades;
        player.hand[2] = fiveOfClubs;
        player.hand[3] = twoOfClubs;
        player.hand[4] = nineOfDiamonds;
        assertNull(player.checkWinnings());
    }

    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
    }

}
