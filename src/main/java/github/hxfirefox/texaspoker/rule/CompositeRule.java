package github.hxfirefox.texaspoker.rule;

import github.hxfirefox.texaspoker.game.PokerResult;
import github.hxfirefox.texaspoker.game.PokerResultBuilder;
import github.hxfirefox.texaspoker.game.Round;

/**
 * Created by »ÆÏè on 15-11-7.
 */
public class CompositeRule extends PokerRule {
    @Override
    public PokerResult handle(Round playerRound, Round computerRound) {
        return new PokerResultBuilder().setSuccess(false).build();
    }
}
