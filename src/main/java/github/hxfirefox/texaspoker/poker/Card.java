package github.hxfirefox.texaspoker.poker;

/**
 * Created by »ÆÏè on 15-11-6.
 */
public class Card {
    private final CardSuit cardSuit;
    private final int faceValue;

    public Card(CardSuit cardSuit, int faceValue) {
        this.cardSuit = cardSuit;
        this.faceValue = faceValue;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    public int getFaceValue() {
        return faceValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (faceValue != card.faceValue) return false;
        return cardSuit == card.cardSuit;

    }

    @Override
    public int hashCode() {
        int result = cardSuit.hashCode();
        result = 31 * result + faceValue;
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s%d", cardSuit, faceValue);
    }
}
