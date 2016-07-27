package net.kemitix.blackjack.model;

import java.util.Collection;

/**
 * Creates the cards for the game.
 *
 * @author pcampbell
 */
interface CardProvider {

    /**
     * Creates the cards for the game.
     *
     * @return the cards
     */
    Collection<Card> createCards();
}
