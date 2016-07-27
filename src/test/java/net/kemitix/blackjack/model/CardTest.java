package net.kemitix.blackjack.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Card}.
 *
 * @author pcampbell
 */
public class CardTest {

    private Card card;

    @Before
    public void setUp() throws Exception {
        card = new Card(Suit.DIAMOND, 6);
    }

    @Test
    public void shouldGetSuit() throws Exception {
        assertThat(card.getSuit()).isEqualTo(Suit.DIAMOND);
    }

    @Test
    public void shouldGetValue() throws Exception {
        assertThat(card.getValue()).isEqualTo(6);
    }

    @Test
    public void shouldToString() throws Exception {
        assertThat(card.toString()).isEqualTo("[6d]");
    }

}
