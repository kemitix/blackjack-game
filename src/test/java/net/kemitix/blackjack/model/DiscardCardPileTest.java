package net.kemitix.blackjack.model;

import lombok.val;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;

/**
 * Tests for {@link DiscardPile}.
 *
 * @author pcampbell
 */
public class DiscardCardPileTest {

    private AbstractCardPile cardPile;

    private Card card1;

    private Card card2;

    @Before
    public void setUp() throws Exception {
        cardPile = new DiscardPile();
        card1 = new Card(Suit.DIAMOND, 2);
        card2 = new Card(Suit.CLUB, 12);
    }

    @Test
    public void shouldAddAndRemoveCards() throws Exception {
        //given
        val hand = new HashSet<Card>();
        hand.add(card1);
        hand.add(card2);
        //when
        cardPile.add(hand);
        //then
        assertThat(cardPile.remove()).as("remove the same cards as added")
                                     .containsOnlyOnce(card1, card2);
        assertThat(cardPile.remove()).as(
                "once cards are remove the pile is empty").isEmpty();
    }

}
