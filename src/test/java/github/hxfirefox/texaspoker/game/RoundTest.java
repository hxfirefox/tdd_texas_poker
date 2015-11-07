package github.hxfirefox.texaspoker.game;

import github.hxfirefox.texaspoker.poker.Card;
import org.junit.Test;

import java.util.List;

import static github.hxfirefox.texaspoker.poker.CardSuit.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RoundTest {
    @Test
    public void should_get_copy_of_all_cards_in_round() throws Exception {
        // given
        final Round round = new Round(new Card(C, 4), new Card(D, 11));
        // when
        List<Card> cardList = round.getAllCards();
        List<Card> anotherCardList = round.getAllCards();
        cardList.add(new Card(B, 10));
        // then
        assertThat(cardList.size(), is(3));
        assertThat(anotherCardList.size(), is(2));
    }

    @Test
    public void should_output_all_elements_in_round() throws Exception {
        // given
        final Round round = new Round(new Card(C, 4), new Card(D, 11), new Card(A, 2));
        // when
        final String output = round.toString();
        // then
        assertThat(output, is("C4, D11, A2"));
    }

    @Test
    public void should_be_equal_when_all_cards_in_round_same_suit_and_face_value() throws Exception {
        // given
        final Round round = new Round(new Card(D, 3), new Card(C, 12), new Card(A, 1));
        final Round otherRound = new Round(new Card(D, 3), new Card(C, 12), new Card(A, 1));
        // when
        // then
        assertThat(round.equals(otherRound), is(true));
    }

    @Test
    public void should_be_equal_when_all_cards_in_round_different_suit_but_same_face_value() throws Exception {
        // given
        final Round round = new Round(new Card(D, 3), new Card(C, 12), new Card(A, 1));
        final Round otherRound = new Round(new Card(A, 3), new Card(D, 12), new Card(A, 1));
        // when
        // then
        assertThat(round.equals(otherRound), is(false));
    }

    @Test
    public void should_be_equal_when_all_cards_in_round_same_suit_but_different_face_value() throws Exception {
        // given
        final Round round = new Round(new Card(D, 3), new Card(C, 12), new Card(A, 1));
        final Round otherRound = new Round(new Card(D, 4), new Card(C, 11), new Card(A, 1));
        // when
        // then
        assertThat(round.equals(otherRound), is(false));
    }
}
