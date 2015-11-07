package github.hxfirefox.texaspoker.game;

import github.hxfirefox.texaspoker.poker.Card;
import org.junit.Before;
import org.junit.Test;

import static github.hxfirefox.texaspoker.game.GameWinner.*;
import static github.hxfirefox.texaspoker.poker.CardSuit.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by »ÆÏè on 15-11-7.
 */
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
                new Round(new Card(B, 11), new Card(A, 2), new Card(A, 9), new Card(D, 10), new Card(A, 8));
        final Round computerRound =
                new Round(new Card(D, 4), new Card(A, 8), new Card(C, 5), new Card(C, 6), new Card(B, 12));
        // when
        final PokerResult result = game.play(playerRound, computerRound);
        // then
        assertThat(result.isSuccessful(), is(true));
        assertThat(result.getWinner(), is(COMPUTER));
        assertThat(result.getWinningRound(), is(computerRound));
    }

    @Test
    public void should_the_greatest_face_value_more_greater_one_win_when_both_high_card_no_matter_suit() throws Exception {
        // given
        final Round playerRound =
                new Round(new Card(D, 3), new Card(D, 4), new Card(D, 5), new Card(D, 6), new Card(D, 7));
        final Round computerRound =
                new Round(new Card(D, 5), new Card(A, 6), new Card(C, 7), new Card(C, 8), new Card(B, 9));
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
                new Round(new Card(B, 11), new Card(A, 2), new Card(A, 9), new Card(D, 10), new Card(A, 8));
        final Round computerRound =
                new Round(new Card(D, 4), new Card(A, 8), new Card(C, 10), new Card(C, 6), new Card(B, 11));
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
                new Round(new Card(B, 11), new Card(A, 2), new Card(A, 9), new Card(D, 10), new Card(A, 8));
        final Round computerRound =
                new Round(new Card(D, 4), new Card(A, 4), new Card(C, 10), new Card(C, 6), new Card(B, 11));
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
                new Round(new Card(B, 11), new Card(A, 2), new Card(A, 9), new Card(D, 10), new Card(A, 8));
        final Round computerRound =
                new Round(new Card(D, 4), new Card(A, 8), new Card(C, 5), new Card(C, 6), new Card(B, 12));
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
                new Round(new Card(B, 13), new Card(A, 2), new Card(A, 9), new Card(D, 10), new Card(A, 8));
        final Round computerRound =
                new Round(new Card(D, 4), new Card(A, 8), new Card(C, 5), new Card(C, 6), new Card(B, 12));
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
