package no.haavstr.videopoker;

/**
 * Created by haavstr on 09.04.15.
 */
public enum Winner {
    ROYAL_FLUSH ("Royal flush", 250),
    STRAIGHT_FLUSH ("Straight flush", 50),
    FOUR_OF_A_KIND ("Four of a kind", 25),
    FULL_HOUSE ("Full house", 9),
    FLUSH ("Flush", 6),
    STRAIGHT ("Straight", 4),
    THREE_OF_A_KIND ("Three of a kind", 3),
    TWO_PAIR ("Two pairs", 2),
    PAIR("Pair", 1);

    private final String name;

    public final int value;

    Winner(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String toString(){
        return name;
    }
}
