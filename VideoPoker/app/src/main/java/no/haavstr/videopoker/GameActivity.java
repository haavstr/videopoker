package no.haavstr.videopoker;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by haavstr on 08.04.15.
 */
public class GameActivity extends Activity
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
    private Context context;

    private RetainedFragment dataFragment;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        context = getApplicationContext();

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

        //For each image of the cards a onclicklistener is set to flip the card to either show the front or the back of it.
        for(int i = 0; i < 5; i++) {
            final int fi;
            fi = i;
            final ImageView card = (ImageView) findViewById(getResources().getIdentifier("card"+(i+1), "id", getPackageName()));
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!readyForNewRound) {
                        // Card is marked for change already, so this click means the user wants to undo this. Show the card again
                        if (changePosition[fi]) {
                            changePosition[fi] = false;
                            card.setImageResource(context.getResources().getIdentifier(hand.hand[fi].toString(), "drawable", context.getPackageName()));
                            // Card is not already marked for change, so the user wants to do this. Show a backside of a card to indicate this
                        } else {
                            changePosition[fi] = true;
                            card.setImageResource(R.drawable.red_back);
                        }
                    }
                }
            });


        }

        // Set the TextView for the cash
        cashView = (TextView) findViewById(R.id.cashvalue);

        // Find the retained fragment with deck and hand on activity restarts
        FragmentManager fm = getFragmentManager();
        dataFragment = (RetainedFragment) fm.findFragmentByTag("data");

        // Create the fragment and data on the first start
        if(dataFragment == null) {
            dataFragment = new RetainedFragment();
            fm.beginTransaction().add(dataFragment, "data").commit();
            deck = new Deck();
            hand = new Hand();
            resetChange();
            cash = 100;
            showCash();
            readyForNewRound = true;
        } else {
            deck = dataFragment.getDeck();
            hand = dataFragment.getHand();
            changePosition = dataFragment.getChangePosition();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("readyForNewRound", readyForNewRound);
        savedInstanceState.putBoolean("gameInProgress", gameInProgress);
        savedInstanceState.putInt("cash", cash);
        savedInstanceState.putInt("placeInDeck", placeInDeck);
        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Set the values from the saved instance and update views too see them
        readyForNewRound = savedInstanceState.getBoolean("readyForNewRound");
        gameInProgress = savedInstanceState.getBoolean("gameInProgress");
        cash = savedInstanceState.getInt("cash");
        showCash();
        placeInDeck = savedInstanceState.getInt("placeInDeck");
        updateCards();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //Store hand and deck in retained fragment
        dataFragment.setData(hand, deck, changePosition);
    }


    void showCash() {
        cashView.setText(Integer.toString(cash));
    }

    void gameOver() {
        winMessage.setText(R.string.gameover);
        dealButton.setText(getResources().getString(R.string.newgame));
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

    /* Displayes the cards that are held in hand, show back of the card if the position is marked for change. Updates text on button to correct name */
    void updateCards() {
            for(int i = 0; i < 5; i++) {
                if(changePosition[i]) {
                    ImageView card = (ImageView) findViewById(getResources().getIdentifier("card" + (i + 1), "id", getPackageName()));
                    card.setImageResource(R.drawable.red_back);
                } else {
                    ImageView card = (ImageView) findViewById(getResources().getIdentifier("card" + (i + 1), "id", getPackageName()));
                    card.setImageResource(context.getResources().getIdentifier(hand.hand[i].toString(), "drawable", context.getPackageName()));
                }
            }
            if(readyForNewRound) {
                dealButton.setText(getResources().getString(R.string.deal));
            } else {
                dealButton.setText(getResources().getString(R.string.change));
            }

    }

    void changeCards(){
        for(int i = 0; i <= 4 ; i++) {
            if(changePosition[i]){
                hand.hand[i] = deck.get(placeInDeck);
            }
            placeInDeck++;
        }
        resetChange();
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

        placeInDeck = 0;
        dealButton.setText(getResources().getString(R.string.deal));
        readyForNewRound = true;
    }

    void resetChange() {
        Arrays.fill(changePosition, false);
    }


}
