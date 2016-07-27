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

    private final Suit suit;

    private final int value;

    @Override
    public final String toString() {
        return "[" + value + suit + "]";
    }
}
