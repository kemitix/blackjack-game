package net.kemitix.blackjack.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Player}.
 *
 * @author pcampbell
 */
public class PlayerTest {

    private Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player();
    }

    @Test
    public void shouldSetAndGetChips() throws Exception {
        //when
        player.setChips(200);
        //then
        assertThat(player.getChips()).isEqualTo(200);
    }

}
