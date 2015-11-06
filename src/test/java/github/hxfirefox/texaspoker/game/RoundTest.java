package github.hxfirefox.texaspoker.game;

import github.hxfirefox.texaspoker.poker.Card;
import org.junit.Test;

import java.util.List;

import static github.hxfirefox.texaspoker.poker.CardSuit.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by »ÆÏè on 15-11-6.
 */
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
        assertThat(output, is("C4D11A2"));
    }
}
