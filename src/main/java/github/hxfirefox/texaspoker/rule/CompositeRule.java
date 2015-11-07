package github.hxfirefox.texaspoker.rule;

import github.hxfirefox.texaspoker.game.PokerResult;
import github.hxfirefox.texaspoker.game.PokerResultBuilder;
import github.hxfirefox.texaspoker.game.Round;

public class CompositeRule extends PokerRule {
    @Override
    public PokerResult handle(Round playerRound, Round computerRound) {
        return new PokerResultBuilder().setSuccess(false).build();
    }
}
