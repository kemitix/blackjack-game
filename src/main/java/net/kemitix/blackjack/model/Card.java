package net.kemitix.blackjack.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * A playing card.
 *
 * @author pcampbell
 */
@Getter
@RequiredArgsConstructor
public class Card {

    private static final int ACE_CARD = 1;

    private static final int JACK_CARD = 11;

    private static final int QUEEN_CARD = 12;

    private static final int KING_CARD = 13;

    private final Suit suit;

    private final int value;

    @Override
    public final String toString() {
        String mapped;
        switch (value) {
        case ACE_CARD:
            mapped = "a";
            break;
        case JACK_CARD:
            mapped = "j";
            break;
        case QUEEN_CARD:
            mapped = "q";
            break;
        case KING_CARD:
            mapped = "k";
            break;
        default:
            mapped = Integer.toString(value);
        }
        return "[" + mapped + suit + "]";
    }
}
