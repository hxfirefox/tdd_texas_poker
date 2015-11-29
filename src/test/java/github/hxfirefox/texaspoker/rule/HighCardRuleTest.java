package github.hxfirefox.texaspoker.rule;

import github.hxfirefox.texaspoker.game.PokerResult;
import github.hxfirefox.texaspoker.game.Round;
import github.hxfirefox.texaspoker.poker.Card;
import org.junit.Test;

import java.util.List;

import static github.hxfirefox.texaspoker.game.GameWinner.*;
import static github.hxfirefox.texaspoker.poker.Card.*;
import static github.hxfirefox.texaspoker.poker.CardSuit.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HighCardRuleTest {
    @Test
    public void should_reorder_cards_in_round_by_descending() throws Exception {
        // given
        final Round round =
                new Round(card(A, 2), card(B, 10), card(C, 9), card(D, 3), card(B, 5));
        final HighCardRule rule = new HighCardRule();
        // when
        List<Card> sortedCards = rule.sortDescending(round.getAllCards());
        // then
        assertThat(sortedCards.size(), is(5));
        assertThat(sortedCards.get(0).equals(card(B, 10)), is(true));
        assertThat(sortedCards.get(1).equals(card(C, 9)), is(true));
        assertThat(sortedCards.get(2).equals(card(B, 5)), is(true));
        assertThat(sortedCards.get(3).equals(card(D, 3)), is(true));
        assertThat(sortedCards.get(4).equals(card(A, 2)), is(true));
    }

    @Test
    public void should_return_winner_and_round_when_respective_round_has_different_greatest_face_value_() throws Exception {
        // given
        final Round playerRound =
                new Round(card(A, 3), card(B, 10), card(C, 9), card(D, 2), card(B, 5));
        final Round computerRound =
                new Round(card(A, 3), card(B, 13), card(C, 9), card(D, 2), card(B, 5));
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
                new Round(card(A, 3), card(B, 10), card(C, 7), card(D, 2), card(B, 5));
        final Round computerRound =
                new Round(card(A, 3), card(B, 10), card(C, 9), card(D, 2), card(B, 5));
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
                new Round(card(A, 3), card(C, 10), card(D, 9), card(A, 2), card(C, 5));
        final Round computerRound =
                new Round(card(A, 3), card(B, 10), card(C, 9), card(D, 2), card(B, 5));
        final HighCardRule rule = new HighCardRule();
        // when
        final PokerResult result = rule.handle(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(true));
        assertThat(result.getWinner(), is(DRAW));
        assertThat(result.getWinningRound(), is(playerRound));
    }
}