package net.kemitix.blackjack.model;

import lombok.val;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The default Card Shoe.
 *
 * <p>Uses a Linked List to store the contents of the shoe to allow speedy
 * removal of cards as they are dealt. Unlike a physical shoe where the cards
 * are held in a random order, we use a random number generator to select which
 * card to deal.</p>
 *
 * @author pcampbell
 */
@Component
class DefaultCardShoe implements CardShoe {

    public static final int PERCENT_SCALE = 100;

    private List<Card> contents = new LinkedList<>();

    private Random random = new Random();

    private final int shuffleLimit;

    private float minimumSize;

    private final DiscardPile discardPile;

    private final CardProvider cardProvider;

    /**
     * Constructor.
     *
     * @param discardPile  the discard pile for reloading the shoe when shuffle
     *                     limit is reached
     * @param cardProvider the provider for the cards to use in the game
     * @param shuffleLimit the percentage of cards to deal before shuffling
     *                     (i.e. adding cards from the discard pile)
     */
    @Inject
    DefaultCardShoe(
            final DiscardPile discardPile, final CardProvider cardProvider,
            @Value("${deck.shuffle-limit}") final int shuffleLimit) {
        this.discardPile = discardPile;
        this.cardProvider = cardProvider;
        this.shuffleLimit = shuffleLimit;
    }

    @PostConstruct
    public void loadShoe() {
        contents.addAll(cardProvider.createCards());
        minimumSize = (contents.size() * (PERCENT_SCALE - shuffleLimit))
                / (float) PERCENT_SCALE;
    }

    @Override
    public Card deal() {
        Card card = null;
        if (contents.size() > 0) {
            card = contents.remove(random.nextInt(contents.size()));
        }
        if (contents.size() <= minimumSize) {
            val discards = discardPile.remove();
            if (discards != null) {
                contents.addAll(discards);
            }
        }
        return card;
    }
}
