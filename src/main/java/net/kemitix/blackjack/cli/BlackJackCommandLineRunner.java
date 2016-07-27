package net.kemitix.blackjack.cli;

import javax.inject.Inject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import net.kemitix.blackjack.cli.console.Console;
import net.kemitix.blackjack.model.Player;

/**
 * Command Line for the BlackJack game.
 *
 * @author pcampbell
 */
@Component
class BlackJackCommandLineRunner implements CommandLineRunner {

    private final Console console;

    private final Player player;

    /**
     * Constructor.
     *
     * @param console the console for IO
     * @param player  the player of the game
     */
    @Inject
    BlackJackCommandLineRunner(final Console console, final Player player) {
        this.console = console;
        this.player = player;
    }

    @Override
    @SuppressWarnings("emptyblock")
    public void run(final String... strings) throws Exception {
        while (askToPlayAGame()) {
            // play a game
        }
        console.println("Thank you for playing.");
        console.println("Your final chip count is " + player.getChips() + ".");
    }

    private boolean askToPlayAGame() {
        console.println("Do you want to play a game? (y/N)");
        return console.readLine().matches("[yY]");
    }
}
