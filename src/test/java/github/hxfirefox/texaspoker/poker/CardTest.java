package github.hxfirefox.texaspoker.poker;

import org.junit.Test;

import static github.hxfirefox.texaspoker.poker.CardSuit.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CardTest {
    @Test
    public void should_be_equal_given_same_suit_and_face_value_card() throws Exception {
        // given
        final Card cardA3 = new Card(A, 3);
        final Card anotherCardA3 = new Card(A, 3);
        // when
        // then
        assertThat(cardA3.equals(anotherCardA3), is(true));
    }

    @Test
    public void should_be_unequal_given_same_suit_but_different_face_value_card() throws Exception {
        // given
        final Card cardA3 = new Card(A, 3);
        final Card cardA5 = new Card(A, 5);
        // when
        // then
        assertThat(cardA3.equals(cardA5), is(false));
    }

    @Test
    public void should_be_unequal_given_different_suit_but_same_face_value_card() throws Exception {
        // given
        final Card cardA3 = new Card(A, 3);
        final Card cardB3 = new Card(B, 3);
        // when
        // then
        assertThat(cardA3.equals(cardB3), is(false));
    }

    @Test
    public void should_greater_when_face_value_greater() throws Exception {
        // given
        final Card cardC5 = new Card(C, 5);
        final Card cardC7 = new Card(C, 7);
        // when
        final int compareResult = cardC7.compareTo(cardC5);
        // then
        assertThat(compareResult, is(1));
    }

    @Test
    public void should_less_when_face_value_less() throws Exception {
        // given
        final Card cardC5 = new Card(C, 5);
        final Card cardC7 = new Card(C, 7);
        // when
        final int compareResult = cardC5.compareTo(cardC7);
        // then
        assertThat(compareResult, is(-1));
    }

    @Test
    public void should_equal_when_face_value_equal() throws Exception {
        // given
        final Card cardC5 = new Card(C, 5);
        final Card cardD5 = new Card(D, 5);
        // when
        final int compareResult = cardC5.compareTo(cardD5);
        // then
        assertThat(compareResult, is(0));
    }

    @Test
    public void should_output_card_suit_and_face_value() throws Exception {
        // given
        final Card cardA3 = new Card(A, 3);
        // when
        // then
        assertThat(cardA3.toString(), is("A3"));
    }
}