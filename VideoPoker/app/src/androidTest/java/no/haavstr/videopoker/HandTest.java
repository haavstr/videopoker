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

    @Override
    protected void setUp() throws Exception {


        player.hand[0] = aceOfSpades;
        player.hand[4] = kingOfSpades;
        player.hand[3] = queenOfSpades;
        player.hand[1] = tenOfSpades;
        player.hand[2] = jackOfSpades;

        super.setUp();
    }

    @SmallTest
    public void testCheckWinnings() {
        assertEquals(player.checkWinnings(), Hand.Winner.ROYAL_FLUSH);

    }

    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
    }

}
