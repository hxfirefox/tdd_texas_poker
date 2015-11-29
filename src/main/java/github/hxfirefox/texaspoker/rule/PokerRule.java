package github.hxfirefox.texaspoker.rule;

import github.hxfirefox.texaspoker.game.GameWinner;
import github.hxfirefox.texaspoker.game.PokerResult;
import github.hxfirefox.texaspoker.game.Round;

import static github.hxfirefox.texaspoker.game.GameWinner.*;

public abstract class PokerRule {
    private PokerRule successor;

    public void setSuccessor(PokerRule nextRule) {
        this.successor = nextRule;
    }

    public PokerResult withRule(Round playerRound, Round computerRound) {
        PokerResult result = handle(playerRound, computerRound);
        if (isResultFailAndHasSuccessor(result)) {
            return successor.withRule(playerRound, computerRound);
        }
        return result;
    }

    private boolean isResultFailAndHasSuccessor(PokerResult result) {
        return !result.isSuccessful() && successor != null;
    }

    public abstract PokerResult handle(Round playerRound, Round computerRound);

    public Round generateWinningRound(GameWinner winner, Round playerRound, Round computerRound) {
        return winner == COMPUTER ? computerRound : playerRound;
    }
}
