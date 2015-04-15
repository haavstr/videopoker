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
public class GameActivity extends Activity //implements TaskFragment.TaskCallbacks
{
    private Hand hand;
    private Deck deck;
    private int cash;
    private int placeInDeck = 0; //Indicates where in the deck the next card to be dealt is.
    private boolean changePosition[] = new boolean[5];
    private boolean readyForNewRound;
    private boolean gameInProgress = false;

    private Button dealButton;
    private TextView cashView, winMessage;
    public ImageView card1, card2, card3, card4, card5;
    private Context context;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        context = getApplicationContext();

        /* Give the player som cash to begin game */
        cash = 100;
        cashView = (TextView) findViewById(R.id.cashvalue);
        cashView.setText(Integer.toString(cash));

        /* Instanciate a box for messages related to winnings */
        winMessage = (TextView) findViewById(R.id.winmessage);

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

        // For card-positions 1
        card1 = (ImageView) findViewById(R.id.card1);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!readyForNewRound) {
                    // Card is marked for change already, so this click means the user wants to undo this. Show the card again
                    if (changePosition[0]) {
                        changePosition[0] = false;
                        card1.setImageResource(context.getResources().getIdentifier(hand.hand[0].toString(), "drawable", context.getPackageName()));
                    // Card is not already marked for change, so the user wants to do this. Show a backside of a card to indicate this */
                    } else {
                        changePosition[0] = true;
                        card1.setImageResource(R.drawable.red_back);
                    }
                }
            }
        });

        card2 = (ImageView) findViewById(R.id.card2);
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /* Card is marked for change already, so this click means the user wants to undo this. Show the card again*/
                if (!readyForNewRound) {
                    if (changePosition[1]) {
                        changePosition[1] = false;
                        card2.setImageResource(context.getResources().getIdentifier(hand.hand[1].toString(), "drawable", context.getPackageName()));
                    /* Card is not already marked for change, so the user wants to do this. Show a backside of a card to indicate this */
                    } else {
                        changePosition[1] = true;
                        card2.setImageResource(R.drawable.red_back);
                    }
                }
            }
        });
        card3 = (ImageView) findViewById(R.id.card3);
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /* Card is marked for change already, so this click means the user wants to undo this. Show the card again*/
                if (!readyForNewRound) {
                    if (changePosition[2]) {
                        changePosition[2] = false;
                        card3.setImageResource(context.getResources().getIdentifier(hand.hand[2].toString(), "drawable", context.getPackageName()));
                        /* Card is not already marked for change, so the user wants to do this. Show a backside of a card to indicate this */
                    } else {
                        changePosition[2] = true;
                        card3.setImageResource(R.drawable.red_back);
                    }
                }
            }
        });
        card4 = (ImageView) findViewById(R.id.card4);
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /* Card is marked for change already, so this click means the user wants to undo this. Show the card again*/
                if (!readyForNewRound) {
                    if (changePosition[3]) {
                        changePosition[3] = false;
                        card4.setImageResource(context.getResources().getIdentifier(hand.hand[3].toString(), "drawable", context.getPackageName()));
                        /* Card is not already marked for change, so the user wants to do this. Show a backside of a card to indicate this */
                    } else {
                        changePosition[3] = true;
                        card4.setImageResource(R.drawable.red_back);
                    }
                }
            }
        });
        card5 = (ImageView) findViewById(R.id.card5);
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /* Card is marked for change already, so this click means the user wants to undo this. Show the card again*/
                if (!readyForNewRound) {
                    if (changePosition[4]) {
                        changePosition[4] = false;
                        card5.setImageResource(context.getResources().getIdentifier(hand.hand[4].toString(), "drawable", context.getPackageName()));
                        /* Card is not already marked for change, so the user wants to do this. Show a backside of a card to indicate this */
                    } else {
                        changePosition[4] = true;
                        card5.setImageResource(R.drawable.red_back);
                    }
                }
            }
        });


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
        winMessage.setText("");
        deck.shuffle();
        for(int i = 0; i < 5 ; i++) {
            hand.hand[i] = deck.get(placeInDeck);
            placeInDeck++;
        }
        readyForNewRound = false;
        updateCards();
    }

    void updateCards() {
        card1.setImageResource(context.getResources().getIdentifier(hand.hand[0].toString(), "drawable", context.getPackageName()));
        card2.setImageResource(context.getResources().getIdentifier(hand.hand[1].toString(), "drawable", context.getPackageName()));
        card3.setImageResource(context.getResources().getIdentifier(hand.hand[2].toString(), "drawable", context.getPackageName()));
        card4.setImageResource(context.getResources().getIdentifier(hand.hand[3].toString(), "drawable", context.getPackageName()));
        card5.setImageResource(context.getResources().getIdentifier(hand.hand[4].toString(), "drawable", context.getPackageName()));
        dealButton.setText(getResources().getString(R.string.change));
    }

    void changeCards(){
        for(int i = 0; i <= 4 ; i++) {
            if(changePosition[i]){
                hand.hand[i] = deck.get(placeInDeck);
            }
            placeInDeck++;
        }
        updateCards();
        finishRound();
    }

    void finishRound() {
        Winner outcome = hand.checkWinnings();

        /* If there is any winnings this will be added and updated */
        if (outcome != null) {
            cash += outcome.value;
            showCash();
            winMessage.setText(outcome.toString());
        }

        /* Check to see if there is cash to start another round */
        if(cash <= 0) {
            gameOver();
        }

        resetChange();
        placeInDeck = 0;
        dealButton.setText(getResources().getString(R.string.deal));
        readyForNewRound = true;
    }

    void resetChange() {
        Arrays.fill(changePosition, false);
    }


}
