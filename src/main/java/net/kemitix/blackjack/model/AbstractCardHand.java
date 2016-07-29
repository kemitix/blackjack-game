package net.kemitix.blackjack.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.val;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Implementation of a hand of cards.
 *
 * @author pcampbell
 */
abstract class AbstractCardHand implements CardHand {

    private static final int TARGET_SCORE = 21;

    private static final int HIGH_ACE_VALUE = 11;

    private static final int LOW_ACE_VALUE = 1;

    private static final int FACE_CARD_VALUE = 10;

    @Getter(AccessLevel.PROTECTED)
    private List<Card> hand = new ArrayList<>();

    @Getter
    private int score;

    @Override
    public final boolean has21() {
        return score == TARGET_SCORE;
    }

    @Override
    public final boolean isBust() {
        return score > TARGET_SCORE;
    }

    @Override
    public final boolean handIsEmpty() {
        return hand.isEmpty();
    }

    @Override
    public final void moveCards(final CardPile pile) {
        val cards = new HashSet<Card>(hand.size());
        cards.addAll(hand);
        pile.add(cards);
        hand.clear();
        score = 0;
    }

    @Override
    public final void addCard(final Card card) {
        hand.add(card);
        updateScore();
    }

    private void updateScore() {
        val aces = new AtomicInteger();
        score = hand.stream().mapToInt(card -> {
            int value = card.getValue();
            // convert face cards to 10
            if (value >= Card.JACK_CARD) {
                value = FACE_CARD_VALUE;
            }
            // count aces as high initially
            if (value == LOW_ACE_VALUE) {
                aces.incrementAndGet();
                value = HIGH_ACE_VALUE;
            }
            return value;
        }).sum();
        // convert high aces to low while bust or and high aces left
        while (score > TARGET_SCORE && aces.get() > 0) {
            score = score - (HIGH_ACE_VALUE - LOW_ACE_VALUE);
            aces.decrementAndGet();
        }
    }

    @Override
    public String toString() {
        return hand.stream()
                   .map(Card::toString)
                   .collect(Collectors.joining(" ")) + " (score: " + score
                + ")";
    }
}
