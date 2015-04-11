package no.haavstr.videopoker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by haavstr on 08.04.15.
 */
public class GameActivity extends Activity {
    private Hand hand;
    private Deck deck;
    private int cash;
    private int placeInDeck; //Indicates where in the deck the next card to be dealt is.
    private boolean changePosition[] = new boolean[5];
    public boolean readyForNewRound;

    private Button dealButton;
    private TextView cashView;
    private ImageView card1, card2, card3, card4, card5;
    private Context context;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        context = getApplicationContext();

        /* Give the player som cash to begin game */
        cash = 100;
        cashView = (TextView) findViewById(R.id.cashvalue);
        cashView.setText(Integer.toString(cash));


        /* Give the button for starting new rounds to an onclick-listener */
        dealButton = (Button) findViewById(R.id.deal);
        dealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (readyForNewRound) {
                    newRound();
                } else {
                    changeCards();
                }
            }
        });

        /* For the card-positions */
        card1 = (ImageView) findViewById(R.id.card1);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Card is marked for change already, so this click means the user wants to undo this. Show the card again*/
                if (changePosition[1]) {
                    changePosition[1] = false;
                    card1.setImageResource(context.getResources().getIdentifier(hand.hand[1].toString(), "drawable", context.getPackageName()));
                /* Card is not already marked for change, so the user wants to do this. Show a backside of a card to indicate this */
                } else {
                    changePosition[1] = true;
                    card1.setImageResource(R.drawable.red_back);
                }
            }
        });

        placeInDeck = 0;
        deck = new Deck();
        hand = new Hand();
        resetChange();
        readyForNewRound = true;
    }

    int getCash () {
        return cash;
    }

    void showCash() {
        cashView.setText(Integer.toString(cash));
    }

    void gameOver() {
        /*TODO*/
    }

    void newRound(){
        cash--;
        showCash();
        deck.shuffle();
        for(int i = 1; i <= 5 ; i++) {
            hand.hand[i] = deck.get(placeInDeck);
            placeInDeck++;
        }
    }

    void updateCards() {
        /*TODO*/
    }

    void changeCards(){
        for(int i = 1; i <= 5 ; i++) {
            if(changePosition[i]){
                hand.hand[i] = deck.get(placeInDeck);
            }
        }
        finishRound();
    }

    void finishRound() {
        Winner outcome = hand.checkWinnings();

        /* If there is any winnings this will be added and updated */
        if (outcome != null) {
            cash += outcome.value;
            showCash();
        }

        /* Check to see if there is cash to start another round */
        if(cash <= 0) {
            gameOver();
        }

        resetChange();
        placeInDeck = 0;
        readyForNewRound = true;
    }

    void resetChange() {
        Arrays.fill(changePosition, false);
    }


}
