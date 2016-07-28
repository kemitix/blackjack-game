package net.kemitix.blackjack.model;

/**
 * The stack of shuffled cards.
 *
 * @author pcampbell
 */
public interface CardShoe {

    /**
     * Deals the next card from the shoe.
     *
     * @return a card
     */
    Card deal();

}
