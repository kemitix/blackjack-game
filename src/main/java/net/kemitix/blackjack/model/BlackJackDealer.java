package net.kemitix.blackjack.model;

/**
 * A dealer in a game of BlackJack.
 *
 * @author pcampbell
 */
public interface BlackJackDealer extends CardHand {

    /**
     * Deals a card from the card shoe into the designated card hand.
     *
     * @param hand the hand to deal the card into
     */
    void hit(CardHand hand);

    /**
     * The initial deal at the start of the game, dealing two cards alternating,
     * to the designated hand and the dealer's own hand.
     *
     * @param hand the other hand to deal into
     */
    void initialDeal(CardHand hand);

    /**
     * The dealer's play, using the stand limit set in configuration.
     *
     * <p>The dealer will keep dealing cards to themselves until they pass the
     * stand limit.</p>
     */
    void play();

    /**
     * Returns the dealers hand as string, with the first card dealt
     * 'face-down'.
     *
     * @return the hand as a string
     */
    String getFilteredHandAsString();
}
