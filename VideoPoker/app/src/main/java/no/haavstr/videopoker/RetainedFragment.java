package no.haavstr.videopoker;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/* A Fragment for retaining the Deck and Hand objects created during a game. */
public class RetainedFragment extends Fragment {


    private Hand hand;
    private Deck deck;
    private boolean [] changePosition;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    public void setData(Hand hand, Deck deck, boolean[] changePosition) {
        this.hand = hand;
        this.deck = deck;
        this.changePosition = changePosition;
    }

    public Hand getHand() {
        return hand;
    }

    public Deck getDeck() {
        return deck;
    }

    public boolean [] getChangePosition() {
        return changePosition;
    }
}
