package github.hxfirefox.texaspoker.rule;

import com.google.common.annotations.VisibleForTesting;
import github.hxfirefox.texaspoker.game.GameWinner;
import github.hxfirefox.texaspoker.game.PokerResult;
import github.hxfirefox.texaspoker.game.PokerResultBuilder;
import github.hxfirefox.texaspoker.game.Round;
import github.hxfirefox.texaspoker.poker.Card;
import github.hxfirefox.texaspoker.poker.DescendCompare;

import java.util.Collections;
import java.util.List;

import static github.hxfirefox.texaspoker.game.GameWinner.*;

public class HighCardRule extends PokerRule {
    private static final int MAX_NUMBER_CARDS = 5;
    public static final int BOTH_EQUAL = 0;
    public static final int FORMER_GREATER = 1;

    @Override
    public PokerResult handle(Round playerRound, Round computerRound) {
        final List<Card> sortedPlayerCards = sortDescending(playerRound.getAllCards());
        final List<Card> sortedComputerCards = sortDescending(computerRound.getAllCards());

        final GameWinner winner = generateGameWinner(sortedPlayerCards, sortedComputerCards);
        final Round winningRound = generateWinningRound(winner, playerRound, computerRound);

        return new PokerResultBuilder().setSuccess(true)
                .setWinner(winner)
                .setWinningRound(winningRound)
                .build();
    }

    private GameWinner generateGameWinner(List<Card> sortedPlayerCards, List<Card> sortedComputerCards) {
        for (int index = 0; index < MAX_NUMBER_CARDS; index++) {
            final int compare = sortedPlayerCards.get(index).compareTo(sortedComputerCards.get(index));
            if (!isCompareEqual(compare)) {
                return getGameWinnerFromUnequalResult(compare);
            }
        }
        return DRAW;
    }

    private boolean isCompareEqual(int compare) {
        return compare == BOTH_EQUAL;
    }

    private GameWinner getGameWinnerFromUnequalResult(int compare) {
        return compare == FORMER_GREATER ? PLAYER : COMPUTER;
    }

    @VisibleForTesting
    protected List<Card> sortDescending(List<Card> allCards) {
        Collections.sort(allCards, new DescendCompare());
        return allCards;
    }
}
