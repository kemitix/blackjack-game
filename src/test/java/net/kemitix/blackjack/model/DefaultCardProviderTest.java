package net.kemitix.blackjack.model;

import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link DefaultCardProvider}.
 *
 * @author pcampbell
 */
public class DefaultCardProviderTest {

    private DefaultCardProvider cardProvider;

    @Test
    public void createSingleDeck() {
        //when
        cardProvider = new DefaultCardProvider(1);
        //then
        assertThat(cardProvider.createCards()).hasSize(52);
    }

    @Test
    public void createDoubleDeck() {
        //when
        cardProvider = new DefaultCardProvider(2);
        //then
        assertThat(cardProvider.createCards()).hasSize(104);
    }

    @Test
    public void cardsShouldHaveValidValue() {
        //given
        cardProvider = new DefaultCardProvider(1);
        //when
        val cards = cardProvider.createCards();
        //then
        assertThat(cards).allMatch(
                card -> card.getValue() >= 1 && card.getValue() <= 13);
    }

}
