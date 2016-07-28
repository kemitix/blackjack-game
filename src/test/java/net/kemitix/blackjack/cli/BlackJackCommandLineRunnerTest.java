package net.kemitix.blackjack.cli;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import net.kemitix.blackjack.cli.console.Console;
import net.kemitix.blackjack.game.BlackJackGame;
import net.kemitix.blackjack.model.Player;

/**
 * Tests for {@link BlackJackCommandLineRunner}.
 *
 * @author pcampbell
 */
public class BlackJackCommandLineRunnerTest {

    private BlackJackCommandLineRunner runner;

    @Mock
    private Console console;

    private Player player;

    @Mock
    private BlackJackGame game;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        player = new Player();
        runner = new BlackJackCommandLineRunner(console, player, game);
    }

    @Test
    public void quitGameIfUserDoesNotWantToPlay() throws Exception {
        //given
        given(console.readLine()).willReturn("n");
        player.setChips(100);
        //when
        runner.run();
        //then
        verify(console).println("Do you want to play a game? (y/N)");
        verify(console).println("Thank you for playing.");
        verify(console).println("Your final chip count is 100.");
    }

    @Test
    public void playTwoGames() throws Exception {
        //given
        given(console.readLine()).willReturn("y", "y", "n");
        player.setChips(100);
        //when
        runner.run();
        verify(game, times(2)).play();
    }

}
