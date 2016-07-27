package net.kemitix.blackjack.model;

/**
 * A player in a game of BlackJack.
 *
 * @author pcampbell
 */
public interface BlackJackPlayer extends CardHand {

    /**
     * Returns the number to chips the player has.
     *
     * @return the number of chips
     */
    int getChips();

    /**
     * Updates the number of chips a player has.
     *
     * @param chips the new number of chips
     */
    void setChips(int chips);
}
