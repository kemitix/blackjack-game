package net.kemitix.blackjack.game;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import net.kemitix.blackjack.cli.console.Console;
import net.kemitix.blackjack.model.BlackJackDealer;
import net.kemitix.blackjack.model.BlackJackPlayer;
import net.kemitix.blackjack.model.DiscardPile;

/**
 * Tests for {@link SimplifiedBlackJackGame}.
 *
 * @author pcampbell
 */
public class SimplifiedBlackJackGameTest {

    private static final int STARTING_CHIPS = 100;

    @InjectMocks
    private SimplifiedBlackJackGame game;

    @Mock
    private Console console;

    @Mock
    private BlackJackPlayer player;

    @Mock
    private BlackJackDealer dealer;

    @Mock
    private DiscardPile discardPile;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        // players initial chips
        given(player.getChips()).willReturn(STARTING_CHIPS);
    }

    @Test
    public void playWherePlayerGetsNatural21() {
        //given
        playersStakeIs(10);
        // honest we won
        given(player.has21()).willReturn(true);
        //when
        game.play();
        //then
        // player won their stake back
        playerWins(10);
    }

    @Test
    public void playWherePlayerHitsAndGoesBust() {
        //given
        playersStakeAndHitMeOnce(10);
        playerGoesBust(true);
        //when
        game.play();
        //then
        // the player was hit
        verify(dealer).hit(player);
        // the player lost their stake
        playerLost(10);
    }

    @Test
    public void playWherePlayerHitsAndHits21() {
        //given
        playersStakeAndHitMeOnce(10);
        // not won yet, but won after hit
        given(player.has21()).willReturn(false, true);
        playerHasCardsInHand(false);
        playerGoesBust(false);
        //when
        game.play();
        //then
        // the player was hit
        verify(dealer).hit(player);
        // the player won their stake
        playerWins(10);
    }

    @Test
    public void playWherePlayerHitsAndHits20() {
        //given
        playersStakeAndHitMeOnce(10);
        playersScoreIs(20);
        playerHasCardsInHand(true);
        playerGoesBust(false);
        dealerGoesBust(false);
        dealerScoreIs(19);
        //when
        game.play();
        //then
        // the player was hit
        verify(dealer).hit(player);
        // the player won their stake
        playerWins(10);
    }

    @Test
    public void playWherePlayerStandsOn18AndDealerGoesBust() {
        //given
        playersStakeIs(10);
        playersScoreIs(18);
        playerHasCardsInHand(true);
        dealerGoesBust(true);
        //when
        game.play();
        //then
        playerWins(10);
    }

    @Test
    public void playWherePlayerStandsOn18AndDealerDraws19() {
        //given
        playersStakeIs(10);
        playersScoreIs(18);
        playerHasCardsInHand(true);
        dealerGoesBust(false);
        dealerScoreIs(19);
        //when
        game.play();
        //then
        playerLost(10);
    }

    @Test
    public void playWherePlayerStandsOn18AndDealerDraws18() {
        //given
        playersStakeIs(10);
        playersScoreIs(18);
        playerHasCardsInHand(true);
        dealerGoesBust(false);
        dealerScoreIs(18);
        //when
        game.play();
        //then
        gameIsStandOff();
    }

    @Test
    public void playWherePlayerStandsOn18AndDealerDraws17() {
        //given
        playersStakeIs(10);
        playersScoreIs(18);
        playerHasCardsInHand(true);
        dealerGoesBust(false);
        dealerScoreIs(17);
        //when
        game.play();
        //then
        playerWins(10);
    }

    private void gameIsStandOff() {
        verify(player, never()).setChips(anyInt());
    }

    private void dealerScoreIs(final int score) {
        given(dealer.getScore()).willReturn(score);
    }

    private void playerLost(final int stake) {
        verify(console).println("You lost!");
        verify(player).setChips(STARTING_CHIPS - stake);
    }

    private void playerWins(final int stake) {
        verify(console).println("You won!");
        verify(player).setChips(STARTING_CHIPS + stake);
    }

    private void playersStakeIs(final Integer stake) {
        // the players stake
        given(console.readLine()).willReturn(stake.toString());
    }

    private void playersStakeAndHitMeOnce(final Integer stake) {
        // the players stake and yes to hit me
        given(console.readLine()).willReturn(stake.toString(), "y", "n");
    }

    private void playerGoesBust(final boolean value) {
        given(player.isBust()).willReturn(value);
    }

    private void dealerGoesBust(final boolean value) {
        given(dealer.isBust()).willReturn(value);
    }

    private void playerHasCardsInHand(final boolean value) {
        given(player.handIsEmpty()).willReturn(!value);
    }

    private void playersScoreIs(final int score) {
        given(player.getScore()).willReturn(score);
    }
}
