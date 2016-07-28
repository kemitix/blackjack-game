package net.kemitix.blackjack.model;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * Tests for {@link DefaultCardShoe}.
 *
 * @author pcampbell
 */
public class DefaultCardShoeTest {

    private DefaultCardShoe cardShoe;

    private DiscardPile discardPile;

    @Mock
    private CardProvider cardProvider;

    private int shuffleLimit;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        shuffleLimit = 75;
        discardPile = new DiscardPile();
        cardShoe = new DefaultCardShoe(discardPile, cardProvider, shuffleLimit);
    }

    @Test
    public void calculateMinimumSizeFromShuffleLimit() {
        //given
        val size = 5;
        val shuffleLimit = 75;
        //when
        float minimumSize = (size * (100 - shuffleLimit)) / (float) 100;
        //then
        assertThat(minimumSize).isEqualTo(1.25f);
    }

    @Test
    public void dealAllCards() {
        //given
        // 5 cards
        Card card1 = new Card(Suit.DIAMOND, 1);
        Card card2 = new Card(Suit.SPADE, 2);
        Card card3 = new Card(Suit.HEART, 3);
        Card card4 = new Card(Suit.CLUB, 4);
        Card card5 = new Card(Suit.CLUB, 8);
        val allCards = Arrays.asList(card1, card2, card3, card4, card5);

        // load the shoe with only 4
        val initialSet = Arrays.asList(card1, card2, card3, card4);
        doReturn(initialSet).when(cardProvider).createCards();
        cardShoe.loadShoe();

        // put fifth card in discard pile
        val discards = new HashSet<Card>();
        discards.add(card5);
        // return the discards then null for empty
        discardPile.add(Collections.singleton(card5));

        //then
        assertThat(cardShoe.deal()).isIn(initialSet);
        assertThat(cardShoe.deal()).isIn(initialSet);
        assertThat(cardShoe.deal()).isIn(initialSet);
        // 75% of 4 cards is hit after 3 cards dealt
        // should be two cards in the shoe now
        assertThat(cardShoe.deal()).isIn(allCards);
        assertThat(cardShoe.deal()).isIn(allCards);
        // shoe should now be empty
        assertThat(cardShoe.deal()).isNull();
    }

}
