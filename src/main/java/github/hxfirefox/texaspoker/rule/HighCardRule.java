package github.hxfirefox.texaspoker.rule;

import com.google.common.annotations.VisibleForTesting;
import github.hxfirefox.texaspoker.game.PokerResult;
import github.hxfirefox.texaspoker.game.PokerResultBuilder;
import github.hxfirefox.texaspoker.game.Round;
import github.hxfirefox.texaspoker.poker.Card;
import github.hxfirefox.texaspoker.poker.DescendCompare;

import java.util.Collections;
import java.util.List;

import static github.hxfirefox.texaspoker.game.GameWinner.*;

/**
 * Created by »ÆÏè on 15-11-6.
 */
public class HighCardRule extends PokerRule{
    private static final int MAX_NUMBER_CARDS = 5;

    @Override
    public PokerResult handle(Round playerRound, Round computerRound) {
        final List<Card> sortedPlayerCards = sortDescending(playerRound.getAllCards());
        final List<Card> sortedComputerCards = sortDescending(computerRound.getAllCards());

        final PokerResultBuilder resultBuilder = new PokerResultBuilder();
        for(int index = 0; index < MAX_NUMBER_CARDS; index++) {
            final int compare = sortedPlayerCards.get(index).compareTo(sortedComputerCards.get(index));
            if (compare == 1) {
                return resultBuilder
                        .setSuccess(true)
                        .setWinner(PLAYER)
                        .setWinningRound(playerRound)
                        .build();
            } else if (compare == -1) {
                return resultBuilder
                        .setSuccess(true)
                        .setWinner(COMPUTER)
                        .setWinningRound(computerRound)
                        .build();
            }
        }
        return resultBuilder.setSuccess(true)
                .setWinner(DRAW)
                .setWinningRound(playerRound)
                .build();
    }

    @VisibleForTesting
    protected List<Card> sortDescending(List<Card> allCards) {
        Collections.sort(allCards, new DescendCompare());
        return allCards;
    }
}
