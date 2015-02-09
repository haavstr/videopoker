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
    Card fiveOfClubs = new Card(Card.Suit.CLUBS, Card.CardValue.FIVE);
    Card fiveOfSpades = new Card(Card.Suit.SPADES, Card.CardValue.FIVE);

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    public void testCheckWinnings() {
        player.hand[0] = aceOfSpades;
        player.hand[4] = kingOfSpades;
        player.hand[3] = queenOfSpades;
        player.hand[1] = tenOfSpades;
        player.hand[2] = jackOfSpades;
        assertEquals(player.checkWinnings(), Hand.Winner.ROYAL_FLUSH);
        player.hand[3] = fiveOfSpades;
        assertNotSame(player.checkWinnings(), Hand.Winner.ROYAL_FLUSH);
        assertEquals(player.checkWinnings(), Hand.Winner.FLUSH);
        player.hand[2] = fiveOfClubs;
        assertNotSame(player.checkWinnings(), Hand.Winner.FLUSH);
    }

    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
    }

}
