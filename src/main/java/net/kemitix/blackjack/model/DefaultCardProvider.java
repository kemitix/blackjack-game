package net.kemitix.blackjack.model;

import lombok.val;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The default Card Provider.
 *
 * @author pcampbell
 */
@Component
class DefaultCardProvider implements CardProvider {

    private static final int CARDS_PER_DECK = 52;

    private static final int CARDS_IN_SUIT = 13;

    private final int deckCount;

    /**
     * Constructor.
     *
     * @param deckCount the number of decks to create
     */
    @Inject
    DefaultCardProvider(@Value("${deck.number-of}") final int deckCount) {
        this.deckCount = deckCount;
    }

    @Override
    public Collection<Card> createCards() {
        val cards = new ArrayList<Card>(deckCount * CARDS_PER_DECK);
        for (int deck = 0; deck < deckCount; deck++) {
            for (int value = 0; value < CARDS_IN_SUIT; value++) {
                final int finalValue = value + 1;
                Arrays.asList(Suit.values()).forEach(suit -> {
                    cards.add(new Card(suit, finalValue));
                });
            }
        }
        return cards;
    }
}
