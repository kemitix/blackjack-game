package net.kemitix.blackjack.model;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link Dealer}.
 *
 * @author pcampbell
 */
public class DealerTest {

    private Dealer dealer;

    @Mock
    private CardShoe cardShoe;

    @Mock
    private CardHand cardHand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        dealer = new Dealer(cardShoe, 17);
    }

    @Test
    public void shouldHit() throws Exception {
        //given
        val card = new Card(Suit.DIAMOND, 3);
        given(cardShoe.deal()).willReturn(card);
        //when
        dealer.hit(cardHand);
        //then
        verify(cardHand).addCard(card);
    }

    @Test
    public void shouldInitialDeal() throws Exception {
        //given
        val card1 = new Card(Suit.DIAMOND, 3);
        val card2 = new Card(Suit.DIAMOND, 4);
        val card3 = new Card(Suit.DIAMOND, 5);
        val card4 = new Card(Suit.DIAMOND, 6);
        given(cardShoe.deal()).willReturn(card1, card2, card3, card4);
        //when
        dealer.initialDeal(cardHand);
        //then
        verify(cardHand, times(2)).addCard(any(Card.class));
        assertThat(dealer.handIsEmpty()).isFalse();
    }

    @Test
    public void playShouldDealThenStand() throws Exception {
        //given
        val card1 = new Card(Suit.SPADE, 6);
        val card2 = new Card(Suit.SPADE, 7);
        val card3 = new Card(Suit.SPADE, 5);
        dealer.addCard(card1);
        dealer.addCard(card2);
        // dealer score is 13
        given(cardShoe.deal()).willReturn(card3);
        //when
        dealer.play();
        //then
        assertThat(dealer.getScore()).isEqualTo(18);
        // only dealt once
        verify(cardShoe).deal();
    }

    @Test
    public void playShouldJustStand() throws Exception {
        //given
        val card1 = new Card(Suit.SPADE, 10);
        val card2 = new Card(Suit.SPADE, 8);
        dealer.addCard(card1);
        dealer.addCard(card2);
        // dealer score is 18
        //when
        dealer.play();
        //then
        assertThat(dealer.getScore()).isEqualTo(18);
        // only dealt once
        verify(cardShoe, never()).deal();
    }

    @Test
    public void playShouldDealUntilBust() throws Exception {
        //given
        val card1 = new Card(Suit.SPADE, 10);
        val card2 = new Card(Suit.SPADE, 5);
        val card3 = new Card(Suit.SPADE, 1);
        val card4 = new Card(Suit.SPADE, 9);
        dealer.addCard(card1);
        dealer.addCard(card2);
        // dealer score is 16
        given(cardShoe.deal()).willReturn(card3, card4);
        //when
        dealer.play();
        //then
        assertThat(dealer.isBust()).isTrue();
        assertThat(dealer.getScore()).isEqualTo(25);
        // only dealt once
        verify(cardShoe, times(2)).deal();
    }

    @Test
    public void shouldHideFirstCardInToString() throws Exception {
        //given
        val card1 = new Card(Suit.SPADE, 10);
        val card2 = new Card(Suit.SPADE, 5);
        dealer.addCard(card1);
        dealer.addCard(card2);
        //then
        assertThat(dealer.getFilteredHandAsString()).isEqualTo("[??] [5s]");
    }
}
