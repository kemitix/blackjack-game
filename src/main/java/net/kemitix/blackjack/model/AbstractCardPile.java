package net.kemitix.blackjack.model;

import lombok.val;

import java.util.HashSet;
import java.util.Set;

/**
 * A generic Card Pile.
 *
 * @author pcampbell
 */
public abstract class AbstractCardPile implements CardPile {

    private Set<Card> pile = new HashSet<>();

    @Override
    public final void add(final Set<Card> cards) {
        pile.addAll(cards);
    }

    @Override
    public final Set<Card> remove() {
        val cards = new HashSet<Card>(pile);
        pile.clear();
        return cards;
    }
}
