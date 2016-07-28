package net.kemitix.blackjack.game;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import net.kemitix.blackjack.cli.console.Console;
import net.kemitix.blackjack.model.BlackJackDealer;
import net.kemitix.blackjack.model.BlackJackPlayer;
import net.kemitix.blackjack.model.DiscardPile;

/**
 * A Simplified Game of BlackJack.
 *
 * <p>The simplifications from a normal game of BlackJack are:</p>
 * <ul>
 * <li>ignore insurance</li>
 * <li>ignore double down</li>
 * <li>ignore split</li>
 * <li>ignore surrender</li>
 * </ul>
 *
 * @author pcampbell
 */
@Component
class SimplifiedBlackJackGame implements BlackJackGame {

    private final Console console;

    private final BlackJackPlayer player;

    private final BlackJackDealer dealer;

    private final DiscardPile discardPile;

    /**
     * Constructor.
     *
     * @param console     the command line interface
     * @param player      the game player
     * @param dealer      the game dealer
     * @param discardPile the discard pile
     */
    @Inject
    SimplifiedBlackJackGame(
            final Console console, final BlackJackPlayer player,
            final BlackJackDealer dealer, final DiscardPile discardPile) {
        this.console = console;
        this.player = player;
        this.dealer = dealer;
        this.discardPile = discardPile;
    }

    /**
     * Plays a single game of BlackJack.
     */
    @Override
    public void play() {
        int stake = getStake();
        dealer.initialDeal(player);
        if (player.has21()) {
            playerWon(stake);
        } else {
            playersPlay(stake);
            // players hand will be empty if they have already won or lost
            if (!player.handIsEmpty()) {
                dealersPlay(stake);
            }
        }
    }

    private int getStake() {
        final int playerChips = player.getChips();
        console.println(
                "How much to you want to stake? (max: " + playerChips + ")");
        return Integer.min(playerChips, Integer.parseInt(console.readLine()));
    }

    private void playersPlay(final int stake) {
        boolean gameOver = false;
        while (!gameOver && playerWantsToHit()) {
            dealer.hit(player);
            if (player.isBust()) {
                playerLost(stake);
                gameOver = true;
            } else if (player.has21()) {
                playerWon(stake);
                gameOver = true;
            }
        }
    }

    private boolean playerWantsToHit() {
        console.println("Do you want to Hit? (y/n)");
        return console.readLine().matches("[yY]");
    }

    private void dealersPlay(final int stake) {
        dealer.play();
        if (dealer.isBust() || player.getScore() > dealer.getScore()) {
            playerWon(stake);
        } else if (player.getScore() < dealer.getScore()) {
            playerLost(stake);
        }
        // if scores are the same then stand off and no change in players chips
        dealer.moveCards(discardPile);
    }

    private void playerLost(final int stake) {
        console.println("You lost!");
        player.setChips(player.getChips() - stake);
        player.moveCards(discardPile);
    }

    private void playerWon(final int stake) {
        console.println("You won!");
        player.setChips(player.getChips() + stake);
        player.moveCards(discardPile);
    }

}
