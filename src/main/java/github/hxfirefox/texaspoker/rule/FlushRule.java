package github.hxfirefox.texaspoker.rule;

import github.hxfirefox.texaspoker.game.GameWinner;
import github.hxfirefox.texaspoker.game.PokerResult;
import github.hxfirefox.texaspoker.game.PokerResultBuilder;
import github.hxfirefox.texaspoker.game.Round;
import github.hxfirefox.texaspoker.poker.Card;
import github.hxfirefox.texaspoker.poker.CardSuit;

import java.util.HashSet;
import java.util.List;

import static github.hxfirefox.texaspoker.game.GameWinner.*;

/**
 * Created by 黄翔 on 15-11-7.
 */
public class FlushRule extends PokerRule {
    private static final int FIRST = 0;
    public static final int SECOND = 1;

    @Override
    public PokerResult handle(Round playerRound, Round computerRound) {
        final boolean playerHasFlush = hasFlush(playerRound.getAllCards());
        final boolean computerHasFlush = hasFlush(computerRound.getAllCards());

        final PokerResultBuilder builder = new PokerResultBuilder();

        if (playerHasFlush == computerHasFlush) {
            return builder.setSuccess(false).build();
        }

        final GameWinner winner = generateGameWinner(playerHasFlush);
        final Round winningRound = generateWinningRound(winner, playerRound, computerRound);

        return builder.setSuccess(true)
                .setWinner(winner)
                .setWinningRound(winningRound)
                .build();
    }

    private GameWinner generateGameWinner(boolean playerHasFlush) {
        return playerHasFlush ? PLAYER : COMPUTER;
    }

    public boolean hasFlush(List<Card> allCards) {
        HashSet<CardSuit> cardSuitSet = new HashSet();

        cardSuitSet.add(allCards.get(FIRST).getCardSuit());
        for (int index = SECOND; index < allCards.size(); index++) {
            if (cardSuitSet.add(allCards.get(index).getCardSuit())) {
                return false;
            }
        }
        return true;
    }
}
