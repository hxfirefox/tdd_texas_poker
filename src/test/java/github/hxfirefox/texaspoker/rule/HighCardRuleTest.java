package github.hxfirefox.texaspoker.rule;

import github.hxfirefox.texaspoker.game.PokerResult;
import github.hxfirefox.texaspoker.game.Round;
import github.hxfirefox.texaspoker.poker.Card;
import org.junit.Test;

import java.util.List;

import static github.hxfirefox.texaspoker.game.GameWinner.*;
import static github.hxfirefox.texaspoker.poker.CardSuit.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by »ÆÏè on 15-11-7.
 */
public class HighCardRuleTest {
    @Test
    public void should_reorder_cards_in_round_by_descending() throws Exception {
        // given
        final Round round =
                new Round(new Card(A, 1), new Card(B, 10), new Card(C, 9), new Card(D, 2), new Card(B, 5));
        final HighCardRule rule = new HighCardRule();
        // when
        List<Card> sortedCards = rule.sortDescending(round.getAllCards());
        // then
        assertThat(sortedCards.size(), is(5));
        assertThat(sortedCards.get(0).equals(new Card(B, 10)), is(true));
        assertThat(sortedCards.get(1).equals(new Card(C, 9)), is(true));
        assertThat(sortedCards.get(2).equals(new Card(B, 5)), is(true));
        assertThat(sortedCards.get(3).equals(new Card(D, 2)), is(true));
        assertThat(sortedCards.get(4).equals(new Card(A, 1)), is(true));
    }

    @Test
    public void should_return_winner_and_round_when_respective_round_has_different_greatest_face_value_() throws Exception {
        // given
        final Round playerRound =
                new Round(new Card(A, 1), new Card(B, 10), new Card(C, 9), new Card(D, 2), new Card(B, 5));
        final Round computerRound =
                new Round(new Card(A, 1), new Card(B, 13), new Card(C, 9), new Card(D, 2), new Card(B, 5));
        final HighCardRule rule = new HighCardRule();
        // when
        final PokerResult result = rule.handle(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(true));
        assertThat(result.getWinner(), is(COMPUTER));
        assertThat(result.getWinningRound(), is(computerRound));
    }

    @Test
    public void should_return_winner_and_round_when_respective_round_has_different_next_greatest_face_value_() throws Exception {
        // given
        final Round playerRound =
                new Round(new Card(A, 1), new Card(B, 10), new Card(C, 7), new Card(D, 2), new Card(B, 5));
        final Round computerRound =
                new Round(new Card(A, 1), new Card(B, 10), new Card(C, 9), new Card(D, 2), new Card(B, 5));
        final HighCardRule rule = new HighCardRule();
        // when
        final PokerResult result = rule.handle(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(true));
        assertThat(result.getWinner(), is(COMPUTER));
        assertThat(result.getWinningRound(), is(computerRound));
    }

    @Test
    public void should_return_draw_and_round_when_respective_round_has_same_face_value_() throws Exception {
        // given
        final Round playerRound =
                new Round(new Card(A, 1), new Card(C, 10), new Card(D, 9), new Card(A, 2), new Card(C, 5));
        final Round computerRound =
                new Round(new Card(A, 1), new Card(B, 10), new Card(C, 9), new Card(D, 2), new Card(B, 5));
        final HighCardRule rule = new HighCardRule();
        // when
        final PokerResult result = rule.handle(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(true));
        assertThat(result.getWinner(), is(DRAW));
        assertThat(result.getWinningRound(), is(playerRound));
    }
}