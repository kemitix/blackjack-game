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
public class Dealer extends AbstractCardHand implements BlackJackDealer {

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

    @Override
    public final void hit(final CardHand hand) {
        hand.addCard(cardShoe.deal());
    }

    @Override
    public final void initialDeal(final CardHand hand) {
        hit(hand);
        hit(this);
        hit(hand);
        hit(this);
    }

    @Override
    public final void play() {
        while (getScore() < standLimit) {
            hit(this);
        }
    }
}
