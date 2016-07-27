package net.kemitix.blackjack.model;

import java.util.Set;

/**
 * A pile of cards.
 *
 * @author pcampbell
 */
public interface CardPile {

    /**
     * Add the cards to the pile.
     *
     * @param cards the cards to add
     */
    void add(Set<Card> cards);

    /**
     * Removes, and returns, all the cards in the pile.
     *
     * @return the cards from the pile
     */
    Set<Card> remove();

}
