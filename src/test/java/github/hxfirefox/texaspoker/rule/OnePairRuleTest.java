package github.hxfirefox.texaspoker.rule;

import github.hxfirefox.texaspoker.game.PokerResult;
import github.hxfirefox.texaspoker.game.Round;
import github.hxfirefox.texaspoker.poker.Card;
import org.junit.Before;
import org.junit.Test;

import static github.hxfirefox.texaspoker.game.GameWinner.COMPUTER;
import static github.hxfirefox.texaspoker.poker.Card.*;
import static github.hxfirefox.texaspoker.poker.CardSuit.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OnePairRuleTest {
    private OnePairRule rule;

    @Before
    public void setUp() throws Exception {
        rule = new OnePairRule();
    }

    @Test
    public void should_get_pair_card_face_value_when_round_had_pair() throws Exception {
        // given
        final Round round =
                new Round(card(D, 4), card(A, 4), card(C, 10), card(C, 6), card(B, 11));
        // when
        int faceValue = rule.getOnePairFaceValue(round.getAllCards());
        // then
        assertThat(faceValue, is(4));
    }

    @Test
    public void should_get_pair_card_invalid_face_value_when_round_had_no_pair() throws Exception {
        // given
        final Round round =
                new Round(card(D, 5), card(A, 4), card(C, 10), card(C, 6), card(B, 11));
        // when
        int faceValue = rule.getOnePairFaceValue(round.getAllCards());
        // then
        assertThat(faceValue, is(0));
    }

    @Test
    public void should_return_winner_and_round_when_one_round_had_pair_but_other_not() throws Exception {
        // given
        final Round playerRound =
                new Round(card(D, 5), card(A, 4), card(C, 10), card(C, 6), card(B, 11));
        final Round computerRound =
                new Round(card(A, 2), card(B, 2), card(C, 10), card(C, 6), card(B, 11));
        // when
        final PokerResult result = rule.handle(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(true));
        assertThat(result.getWinner(), is(COMPUTER));
        assertThat(result.getWinningRound(), is(computerRound));
    }

    @Test
    public void should_return_winner_and_round_when_one_had_pair_greater_than_other() throws Exception {
        // given
        final Round playerRound =
                new Round(card(D, 5), card(A, 5), card(C, 10), card(C, 6), card(B, 11));
        final Round computerRound =
                new Round(card(A, 11), card(B, 2), card(C, 10), card(C, 6), card(B, 11));
        // when
        final PokerResult result = rule.handle(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(true));
        assertThat(result.getWinner(), is(COMPUTER));
        assertThat(result.getWinningRound(), is(computerRound));
    }

    @Test
    public void should_return_fail_when_one_had_pair_equal_to_other() throws Exception {
        // given
        final Round playerRound =
                new Round(card(D, 11), card(A, 5), card(C, 10), card(C, 6), card(C, 11));
        final Round computerRound =
                new Round(card(A, 11), card(B, 2), card(C, 10), card(C, 6), card(B, 11));
        // when
        final PokerResult result = rule.handle(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(false));
    }

    @Test
    public void should_return_fail_when_both_had_no_pair() throws Exception {
        // given
        final Round playerRound =
                new Round(card(D, 2), card(A, 5), card(C, 10), card(C, 6), card(C, 11));
        final Round computerRound =
                new Round(card(A, 12), card(B, 2), card(C, 10), card(C, 6), card(B, 11));
        // when
        final PokerResult result = rule.handle(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(false));
    }
}