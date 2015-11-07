package github.hxfirefox.texaspoker.poker;

import java.util.Comparator;

public class DescendCompare implements Comparator {
    public int compare(Object o1, Object o2) {
        final Card card = (Card) o1;
        final Card otherCard = (Card) o2;
        return toDescending(card.compareTo(otherCard));
    }

    private int toDescending(int result) {
        return 0 - result;
    }
}
