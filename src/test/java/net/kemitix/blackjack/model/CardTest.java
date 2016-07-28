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

    @Test
    public void shouldToStringAceClubs() throws Exception {
        assertThat(new Card(Suit.CLUB, 1).toString()).isEqualTo("[ac]");
    }

    @Test
    public void shouldToStringJackSpaced() throws Exception {
        assertThat(new Card(Suit.SPADE, 11).toString()).isEqualTo("[js]");
    }

    @Test
    public void shouldToStringQueenSpaced() throws Exception {
        assertThat(new Card(Suit.SPADE, 12).toString()).isEqualTo("[qs]");
    }

    @Test
    public void shouldToStringKingHearts() throws Exception {
        assertThat(new Card(Suit.HEART, 13).toString()).isEqualTo("[kh]");
    }

}
