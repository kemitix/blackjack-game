package net.kemitix.blackjack.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link Suit}.
 *
 * @author pcampbell
 */
public class SuitTest {

    @Test
    public void spadeToStringIsS() {
        assertThat(Suit.SPADE.toString()).isEqualTo("s");
    }

    @Test
    public void heartToStringIsS() {
        assertThat(Suit.HEART.toString()).isEqualTo("h");
    }

    @Test
    public void diamondToStringIsS() {
        assertThat(Suit.DIAMOND.toString()).isEqualTo("d");
    }

    @Test
    public void clubToStringIsS() {
        assertThat(Suit.CLUB.toString()).isEqualTo("c");
    }
}
