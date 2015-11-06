package github.hxfirefox.texaspoker.rule;

import github.hxfirefox.texaspoker.game.PokerResult;
import github.hxfirefox.texaspoker.game.Round;

/**
 * Created by »ÆÏè on 15-11-6.
 */
public abstract class PokerRule {
    private PokerRule successor;

    public void setSuccessor(PokerRule nextRule) {
        this.successor = nextRule;
    }

    public PokerResult withRule(Round playerRound, Round computerRound){
        PokerResult result = handle(playerRound,computerRound);
        if (!result.isSuccessful() && successor != null) {
            return successor.withRule(playerRound,computerRound);
        }
        return result;
    }

    public abstract PokerResult handle(Round playerRound, Round computerRound);
}
