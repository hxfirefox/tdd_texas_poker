package github.hxfirefox.texaspoker.rule;

import com.google.common.annotations.VisibleForTesting;
import github.hxfirefox.texaspoker.game.GameWinner;
import github.hxfirefox.texaspoker.game.PokerResult;
import github.hxfirefox.texaspoker.game.PokerResultBuilder;
import github.hxfirefox.texaspoker.game.Round;
import github.hxfirefox.texaspoker.poker.Card;

import java.util.HashSet;
import java.util.List;

import static github.hxfirefox.texaspoker.game.GameWinner.*;

/**
 * Created by »ÆÏè on 15-11-7.
 */
public class OnePairRule extends PokerRule {
    private static final int INVALID_FACE_VALUE = 0;

    @Override
    public PokerResult handle(Round playerRound, Round computerRound) {
        final int playerPairFaceValue = getOnePairFaceValue(playerRound.getAllCards());
        final int computerPairFaceValue = getOnePairFaceValue(computerRound.getAllCards());

        final PokerResultBuilder builder = new PokerResultBuilder();
        if (isPairValueEqual(playerPairFaceValue, computerPairFaceValue)) {
            return builder.setSuccess(false).build();
        }

        final GameWinner winner = generateGameWinner(playerPairFaceValue, computerPairFaceValue);
        final Round winningRound = generateWinningRound(winner, playerRound, computerRound);

        return builder.setSuccess(true)
                .setWinner(winner)
                .setWinningRound(winningRound)
                .build();
    }

    private boolean isPairValueEqual(int playerPairFaceValue, int computerPairFaceValue) {
        return playerPairFaceValue == computerPairFaceValue;
    }

    private Round generateWinningRound(GameWinner winner, Round playerRound, Round computerRound) {
        return winner == PLAYER ? playerRound : computerRound;
    }

    private GameWinner generateGameWinner(int playerPairFaceValue, int computerPairFaceValue) {
        return playerPairFaceValue > computerPairFaceValue ? PLAYER : COMPUTER;
    }

    @VisibleForTesting
    protected int getOnePairFaceValue(List<Card> allCards) {
        HashSet<Integer> set = new HashSet();
        for (Card card : allCards) {
            final int faceValue = card.getFaceValue();
            if (!set.add(faceValue)) {
                return faceValue;
            }
        }
        return INVALID_FACE_VALUE;
    }
}
