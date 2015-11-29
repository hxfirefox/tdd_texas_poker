package github.hxfirefox.texaspoker.rule;

import github.hxfirefox.texaspoker.game.PokerResult;
import github.hxfirefox.texaspoker.game.Round;
import github.hxfirefox.texaspoker.poker.Card;
import org.junit.Before;
import org.junit.Test;

import static github.hxfirefox.texaspoker.game.GameWinner.PLAYER;
import static github.hxfirefox.texaspoker.poker.Card.*;
import static github.hxfirefox.texaspoker.poker.CardSuit.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by 黄翔 on 15-11-7.
 */
public class FlushRuleTest {
    private FlushRule rule;

    @Before
    public void setUp() throws Exception {
        rule = new FlushRule();
    }

    @Test
    public void should_return_true_when_cards_flush() throws Exception {
        // given
        Round round =
                new Round(card(D, 3), card(D, 4), card(D, 5), card(D, 6), card(D, 7));
        // when
        final boolean hasFlush = rule.hasFlush(round.getAllCards());
        // then
        assertThat(hasFlush, is(true));
    }

    @Test
    public void should_return_false_when_cards_not_flush() throws Exception {
        // given
        Round round =
                new Round(card(A, 3), card(C, 4), card(D, 5), card(D, 6), card(D, 7));
        // when
        final boolean hasFlush = rule.hasFlush(round.getAllCards());
        // then
        assertThat(hasFlush, is(false));
    }

    @Test
    public void should_return_winner_and_round_when_one_has_flush_other_pair() throws Exception {
        // given
        Round playerRound =
                new Round(card(D, 3), card(D, 4), card(D, 5), card(D, 6), card(D, 7));
        Round computerRound =
                new Round(card(D, 3), card(B, 3), card(A, 5), card(D, 6), card(D, 7));
        // when
        final PokerResult result = rule.withRule(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(true));
        assertThat(result.getWinner(), is(PLAYER));
        assertThat(result.getWinningRound(), is(playerRound));
    }

    @Test
    public void should_return_winner_and_round_when_one_has_flush_other_high_card() throws Exception {
        // given
        Round playerRound =
                new Round(card(D, 3), card(D, 4), card(D, 5), card(D, 6), card(D, 7));
        Round computerRound =
                new Round(card(D, 2), card(B, 3), card(A, 5), card(D, 9), card(D, 7));
        // when
        final PokerResult result = rule.withRule(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(true));
        assertThat(result.getWinner(), is(PLAYER));
        assertThat(result.getWinningRound(), is(playerRound));
    }

    @Test
    public void should_return_fail_when_both_have_flush() throws Exception {
        // given
        Round playerRound =
                new Round(card(D, 3), card(D, 4), card(D, 5), card(D, 6), card(D, 7));
        Round computerRound =
                new Round(card(A, 2), card(A, 3), card(A, 5), card(A, 9), card(A, 7));
        // when
        final PokerResult result = rule.withRule(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(false));
    }
}
