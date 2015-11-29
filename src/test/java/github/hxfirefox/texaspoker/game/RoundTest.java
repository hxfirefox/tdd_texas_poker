package github.hxfirefox.texaspoker.game;

import github.hxfirefox.texaspoker.poker.Card;
import org.junit.Test;

import java.util.List;

import static github.hxfirefox.texaspoker.poker.Card.card;
import static github.hxfirefox.texaspoker.poker.CardSuit.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RoundTest {
    @Test
    public void should_get_copy_of_all_cards_in_round() throws Exception {
        // given
        final Round round = new Round(card(C, 4), card(D, 11));
        // when
        List<Card> cardList = round.getAllCards();
        List<Card> anotherCardList = round.getAllCards();
        cardList.add(card(B, 10));
        // then
        assertThat(cardList.size(), is(3));
        assertThat(anotherCardList.size(), is(2));
    }

    @Test
    public void should_output_all_elements_in_round() throws Exception {
        // given
        final Round round = new Round(card(C, 4), card(D, 11), card(A, 2));
        // when
        final String output = round.toString();
        // then
        assertThat(output, is("C4, D11, A2"));
    }

    @Test
    public void should_be_equal_when_all_cards_in_round_same_suit_and_face_value() throws Exception {
        // given
        final Round round = new Round(card(D, 3), card(C, 12), card(A, 2));
        final Round otherRound = new Round(card(D, 3), card(C, 12), card(A, 2));
        // when
        // then
        assertThat(round.equals(otherRound), is(true));
    }

    @Test
    public void should_be_equal_when_all_cards_in_round_different_suit_but_same_face_value() throws Exception {
        // given
        final Round round = new Round(card(D, 3), card(C, 12), card(A, 1));
        final Round otherRound = new Round(card(A, 3), card(D, 12), card(A, 1));
        // when
        // then
        assertThat(round.equals(otherRound), is(false));
    }

    @Test
    public void should_be_equal_when_all_cards_in_round_same_suit_but_different_face_value() throws Exception {
        // given
        final Round round = new Round(card(D, 3), card(C, 12), card(A, 2));
        final Round otherRound = new Round(card(D, 4), card(C, 11), card(A, 2));
        // when
        // then
        assertThat(round.equals(otherRound), is(false));
    }
}
