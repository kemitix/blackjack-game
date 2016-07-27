package net.kemitix.blackjack.model;

/**
 * Interface for a hand of cards in the game.
 *
 * @author pcampbell
 */
public interface CardHand {

    /**
     * Does the hand score equal 21.
     *
     * @return true if the score is 21 exactly
     */
    boolean has21();

    /**
     * Does the hand score exceed 21.
     *
     * @return true if the score is over 21
     */
    boolean isBust();

    /**
     * Does the hand not have any cards.
     *
     * @return true if the hand has no cards
     */
    boolean handIsEmpty();

    /**
     * Returns the score of the hand.
     *
     * <p>Where there are aces and the score exceeds 21 counting them as 11, the
     * aces are each automatically counted as 1. For example: if a hand has two
     * aces and a 9, then one ace will be counted as 11 and the other as 1 to
     * give a score of 21.</p>
     *
     * @return the hands score
     */
    int getScore();

    /**
     * Moves all the cards from the hand to the designated pile.
     *
     * <p>The hand will be empty after this call.</p>
     *
     * @param pile the card pile to move the cards to
     */
    void moveCards(CardPile pile);

    /**
     * Adds the card to the hand.
     *
     * @param card the card to add
     */
    void addCard(Card card);

}
