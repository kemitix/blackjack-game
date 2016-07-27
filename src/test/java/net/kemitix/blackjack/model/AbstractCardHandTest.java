package net.kemitix.blackjack.model;

import lombok.val;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

/**
 * .
 *
 * @author pcampbell
 */
public class AbstractCardHandTest {

    private CardHand cardHand3And4;

    private CardHand cardHand4AndQueen;

    private CardHand cardHand8AndAce;

    private CardHand cardHand8And2Aces;

    private CardHand cardHand10And2Aces;

    private CardHand cardHand9And8And4;

    private CardHand cardHand9And8And5;

    @Mock
    private CardPile cardPile;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        val card3 = new Card(Suit.DIAMOND, 3);
        val card4 = new Card(Suit.DIAMOND, 4);
        val card5 = new Card(Suit.DIAMOND, 5);
        val card8 = new Card(Suit.DIAMOND, 8);
        val card9 = new Card(Suit.DIAMOND, 9);
        val card10 = new Card(Suit.DIAMOND, 10);
        val cardAceD = new Card(Suit.DIAMOND, 11);
        val cardAceS = new Card(Suit.SPADE, 11);
        val cardQueen = new Card(Suit.DIAMOND, 13);
        cardHand3And4 = buildHand(card3, card4);
        cardHand4AndQueen = buildHand(card4, cardQueen);
        cardHand8AndAce = buildHand(card8, cardAceD);
        cardHand8And2Aces = buildHand(card8, cardAceD, cardAceS);
        cardHand10And2Aces = buildHand(card10, cardAceD, cardAceS);
        cardHand9And8And4 = buildHand(card9, card8, card4);
        cardHand9And8And5 = buildHand(card9, card8, card5);
    }

    private CardHand buildHand(final Card... cards) {
        final CardHand hand = new AbstractCardHand() {
        };
        Arrays.asList(cards).forEach(hand::addCard);
        return hand;
    }

    @Test
    public void shouldHas21() throws Exception {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(cardHand3And4.has21()).isFalse();
        softly.assertThat(cardHand4AndQueen.has21()).isFalse();
        softly.assertThat(cardHand8AndAce.has21()).isFalse();
        softly.assertThat(cardHand8And2Aces.has21()).isFalse();
        softly.assertThat(cardHand10And2Aces.has21()).isFalse();
        softly.assertThat(cardHand9And8And4.has21()).isTrue();
        softly.assertThat(cardHand9And8And5.has21()).isFalse();
        softly.assertAll();
    }

    @Test
    public void shouldIsBust() throws Exception {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(cardHand3And4.isBust()).isFalse();
        softly.assertThat(cardHand4AndQueen.isBust()).isFalse();
        softly.assertThat(cardHand8AndAce.isBust()).isFalse();
        softly.assertThat(cardHand8And2Aces.isBust()).isFalse();
        softly.assertThat(cardHand10And2Aces.isBust()).isFalse();
        softly.assertThat(cardHand9And8And4.isBust()).isFalse();
        softly.assertThat(cardHand9And8And5.isBust()).isTrue();
        softly.assertAll();
    }

    @Test
    public void shouldGetScore() throws Exception {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(cardHand3And4.getScore()).isEqualTo(7);
        softly.assertThat(cardHand4AndQueen.getScore()).isEqualTo(14);
        softly.assertThat(cardHand8AndAce.getScore()).isEqualTo(19);
        softly.assertThat(cardHand8And2Aces.getScore()).isEqualTo(20);
        softly.assertThat(cardHand10And2Aces.getScore()).isEqualTo(12);
        softly.assertThat(cardHand9And8And4.getScore()).isEqualTo(21);
        softly.assertThat(cardHand9And8And5.getScore()).isEqualTo(22);
        softly.assertAll();
    }

    @Test
    public void shouldHandIsEmpty() throws Exception {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(buildHand().handIsEmpty()).isTrue();
        softly.assertThat(cardHand3And4.handIsEmpty()).isFalse();
        softly.assertAll();
    }

    @Test
    public void shouldMoveCards() throws Exception {
        //when
        cardHand4AndQueen.moveCards(cardPile);
        //then
        assertThat(cardHand4AndQueen.handIsEmpty()).isTrue();
        verify(cardPile).add(any());
    }
}
