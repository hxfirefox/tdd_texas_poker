package github.hxfirefox.texaspoker.game;

import org.junit.Before;
import org.junit.Test;

import static github.hxfirefox.texaspoker.game.GameWinner.*;
import static github.hxfirefox.texaspoker.poker.Card.card;
import static github.hxfirefox.texaspoker.poker.CardSuit.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TexasPokerGameTest {
    private TexasPokerGame game;

    @Before
    public void setUp() throws Exception {
        game = new TexasPokerGame("Tom");
    }

    @Test
    public void should_the_greatest_face_value_more_greater_one_win_when_both_high_card() throws Exception {
        // given
        final Round playerRound =
                new Round(card(B, 11), card(A, 2), card(A, 9), card(D, 10), card(A, 8));
        final Round computerRound =
                new Round(card(D, 4), card(A, 8), card(C, 5), card(C, 6), card(B, 12));
        // when
        final PokerResult result = game.play(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(true));
        assertThat(result.getWinner(), is(COMPUTER));
        assertThat(result.getWinningRound(), is(computerRound));
    }

    @Test
    public void should_the_second_greatest_face_value_more_greater_one_win_when_both_high_card_with_same_greatest() throws Exception {
        // given
        final Round playerRound =
                new Round(card(B, 11), card(A, 2), card(A, 9), card(D, 10), card(A, 8));
        final Round computerRound =
                new Round(card(D, 4), card(A, 8), card(C, 10), card(C, 6), card(B, 11));
        // when
        final PokerResult result = game.play(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(true));
        assertThat(result.getWinner(), is(PLAYER));
        assertThat(result.getWinningRound(), is(playerRound));
    }

    @Test
    public void should_pair_one_win_when_other_one_high_card() throws Exception {
        // given
        final Round playerRound =
                new Round(card(B, 11), card(A, 2), card(A, 9), card(D, 10), card(A, 8));
        final Round computerRound =
                new Round(card(D, 4), card(A, 4), card(C, 10), card(C, 6), card(B, 11));
        // when
        final PokerResult result = game.play(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(true));
        assertThat(result.getWinner(), is(COMPUTER));
        assertThat(result.getWinningRound(), is(computerRound));
    }

    @Test
    public void should_pair_value_greater_one_win_when_both_had_pair() throws Exception {
        // given
        final Round playerRound =
                new Round(card(B, 11), card(A, 9), card(D, 9), card(D, 5), card(A, 8));
        final Round computerRound =
                new Round(card(D, 4), card(B, 10), card(C, 10), card(C, 6), card(B, 12));
        // when
        final PokerResult result = game.play(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(true));
        assertThat(result.getWinner(), is(COMPUTER));
        assertThat(result.getWinningRound(), is(computerRound));
    }

    @Test
    public void should_high_card_value_greater_one_win_when_both_pair_equal() throws Exception {
        // given
        Round playerRound =
                new Round(card(B, 11), card(A, 9), card(D, 9), card(D, 5), card(A, 8));
        Round computerRound =
                new Round(card(D, 10), card(A, 6), card(C, 9), card(B, 12), card(B, 9));
        // when
        final PokerResult result = game.play(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(true));
        assertThat(result.getWinner(), is(COMPUTER));
        assertThat(result.getWinningRound(), is(computerRound));
    }

    @Test
    public void should_one_has_flush_win_when_other_not() throws Exception {
        // given
        final Round playerRound =
                new Round(card(B, 11), card(A, 2), card(A, 9), card(D, 11), card(A, 8));
        final Round computerRound =
                new Round(card(D, 4), card(D, 8), card(D, 5), card(D, 6), card(D, 12));
        // when
        final PokerResult result = game.play(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(true));
        assertThat(result.getWinner(), is(COMPUTER));
        assertThat(result.getWinningRound(), is(computerRound));
    }

    @Test
    public void should_high_card_greater_win_when_both_have_flush() throws Exception {
        // given
        final Round playerRound =
                new Round(card(D, 3), card(D, 4), card(D, 5), card(D, 6), card(D, 7));
        final Round computerRound =
                new Round(card(B, 5), card(B, 6), card(B, 7), card(B, 8), card(B, 9));
        // when
        final PokerResult result = game.play(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(true));
        assertThat(result.getWinner(), is(COMPUTER));
        assertThat(result.getWinningRound(), is(computerRound));
    }

    @Test
    public void should_output_both_cards_in_round_and_winner_when_computer_win() throws Exception {
        // given
        final Round playerRound =
                new Round(card(B, 11), card(A, 2), card(A, 9), card(D, 10), card(A, 8));
        final Round computerRound =
                new Round(card(D, 4), card(A, 8), card(C, 5), card(C, 6), card(B, 12));
        // when
        final PokerResult result = game.play(playerRound, computerRound);
        final String output = game.outputFinalResult(playerRound, computerRound, result);
        // then
        String expectOutput = "B11, A2, A9, D10, A8\n" +
                "D4, A8, C5, C6, B12\n\n" +
                "Winner: COMPUTER\n" +
                "D4, A8, C5, C6, B12\n";
        assertThat(output, is(expectOutput));
    }

    @Test
    public void should_output_both_cards_in_round_and_winner_name_when_player_win() throws Exception {
        // given
        final Round playerRound =
                new Round(card(B, 13), card(A, 2), card(A, 9), card(D, 10), card(A, 8));
        final Round computerRound =
                new Round(card(D, 4), card(A, 8), card(C, 5), card(C, 6), card(B, 12));
        // when
        final PokerResult result = game.play(playerRound, computerRound);
        final String output = game.outputFinalResult(playerRound, computerRound, result);
        // then
        String expectOutput = "B13, A2, A9, D10, A8\n" +
                "D4, A8, C5, C6, B12\n\n" +
                "Winner: PLAYER Tom\n" +
                "B13, A2, A9, D10, A8\n";
        assertThat(output, is(expectOutput));
    }
}
