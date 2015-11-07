package github.hxfirefox.texaspoker.rule;

import github.hxfirefox.texaspoker.game.PokerResult;
import github.hxfirefox.texaspoker.game.Round;
import github.hxfirefox.texaspoker.poker.Card;
import org.junit.Before;
import org.junit.Test;

import static github.hxfirefox.texaspoker.game.GameWinner.COMPUTER;
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
                new Round(new Card(D, 4), new Card(A, 4), new Card(C, 10), new Card(C, 6), new Card(B, 11));
        // when
        int faceValue = rule.getOnePairFaceValue(round.getAllCards());
        // then
        assertThat(faceValue, is(4));
    }

    @Test
    public void should_get_pair_card_invalid_face_value_when_round_had_no_pair() throws Exception {
        // given
        final Round round =
                new Round(new Card(D, 5), new Card(A, 4), new Card(C, 10), new Card(C, 6), new Card(B, 11));
        // when
        int faceValue = rule.getOnePairFaceValue(round.getAllCards());
        // then
        assertThat(faceValue, is(0));
    }

    @Test
    public void should_return_winner_and_round_when_one_round_had_pair_but_other_not() throws Exception {
        // given
        final Round playerRound =
                new Round(new Card(D, 5), new Card(A, 4), new Card(C, 10), new Card(C, 6), new Card(B, 11));
        final Round computerRound =
                new Round(new Card(A, 2), new Card(B, 2), new Card(C, 10), new Card(C, 6), new Card(B, 11));
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
                new Round(new Card(D, 5), new Card(A, 5), new Card(C, 10), new Card(C, 6), new Card(B, 11));
        final Round computerRound =
                new Round(new Card(A, 11), new Card(B, 2), new Card(C, 10), new Card(C, 6), new Card(B, 11));
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
                new Round(new Card(D, 11), new Card(A, 5), new Card(C, 10), new Card(C, 6), new Card(C, 11));
        final Round computerRound =
                new Round(new Card(A, 11), new Card(B, 2), new Card(C, 10), new Card(C, 6), new Card(B, 11));
        // when
        final PokerResult result = rule.handle(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(false));
    }

    @Test
    public void should_return_fail_when_both_had_no_pair() throws Exception {
        // given
        final Round playerRound =
                new Round(new Card(D, 2), new Card(A, 5), new Card(C, 10), new Card(C, 6), new Card(C, 11));
        final Round computerRound =
                new Round(new Card(A, 12), new Card(B, 2), new Card(C, 10), new Card(C, 6), new Card(B, 11));
        // when
        final PokerResult result = rule.handle(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(false));
    }
}