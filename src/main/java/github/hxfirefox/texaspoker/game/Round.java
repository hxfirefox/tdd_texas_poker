package github.hxfirefox.texaspoker.game;

import com.google.common.collect.Lists;
import github.hxfirefox.texaspoker.poker.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Round {
    public static final String SEPARATOR_COMMA = ", ";
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

        for (int index = 0; index < cardList.size(); index++) {
            output.append(cardList.get(index).toString());
            if (!isLastOfCards(index)) {
                output.append(SEPARATOR_COMMA);
            }
        }

        return output.toString();
    }

    private boolean isLastOfCards(int index) {
        return index == cardList.size() - 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Round round = (Round) o;

        return cardList.equals(round.cardList);

    }

    @Override
    public int hashCode() {
        return cardList.hashCode();
    }
}
