package net.kemitix.blackjack.model;

/**
 * The four card suits.
 *
 * @author pcampbell
 */
public enum Suit {

    SPADE("s"), HEART("h"), DIAMOND("d"), CLUB("c");

    private final String value;

    Suit(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
