package github.hxfirefox.texaspoker.game;

import com.google.common.collect.Lists;
import github.hxfirefox.texaspoker.poker.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by »ÆÏè on 15-11-6.
 */
public class Round {
    private List<Card> cardList = Lists.newArrayList();

    public Round(Card... cards) {
        Collections.addAll(cardList, cards);
    }

    public List<Card> getAllCards() {
        return new ArrayList<Card>(cardList);
    }

    @Override
    public String toString() {
        final StringBuilder output = new StringBuilder();
        for (Card card:cardList) {
            output.append(card.toString());
        }
        return output.toString();
    }
}
