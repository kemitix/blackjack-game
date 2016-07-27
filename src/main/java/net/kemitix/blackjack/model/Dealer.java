package net.kemitix.blackjack.model;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The dealer for the game.
 *
 * @author pcampbell
 */
@Component
public class Dealer extends AbstractCardHand {

    private final CardShoe cardShoe;

    private final int standLimit;

    /**
     * Constructor.
     *
     * @param cardShoe   the card shoe
     * @param standLimit the dealer must stand once reaching this limit
     */
    @Inject
    public Dealer(
            final CardShoe cardShoe,
            @Value("${dealer.stand-limit}") final int standLimit) {
        this.cardShoe = cardShoe;
        this.standLimit = standLimit;
    }

    /**
     * Deals a card from the card shoe into the designated card hand.
     *
     * @param hand the hand to deal the card into
     */
    public final void hit(final CardHand hand) {
        hand.addCard(cardShoe.deal());
    }

    /**
     * The initial deal at the start of the game, dealing two cards alternating,
     * to the designated hand and the dealer's own hand.
     *
     * @param hand the other hand to deal into
     */
    public final void initialDeal(final CardHand hand) {
        hit(hand);
        hit(this);
        hit(hand);
        hit(this);
    }

    /**
     * The dealer's play, using the stand limit set in configuration.
     *
     * <p>The dealer will keep dealing cards to themselves until they pass the
     * stand limit.</p>
     */
    public final void play() {
        while (getScore() < standLimit) {
            hit(this);
        }
    }
}
