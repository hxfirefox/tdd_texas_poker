package github.hxfirefox.texaspoker.game;

import github.hxfirefox.texaspoker.rule.CompositeRule;
import github.hxfirefox.texaspoker.rule.HighCardRule;
import github.hxfirefox.texaspoker.rule.PokerRule;

/**
 * Created by ���� on 15-11-7.
 */
public class TexasPokerGame {
    private PokerRule pokerRule;
    private final String playerName;

    public TexasPokerGame(String playerName) {
        this.playerName = playerName;
        pokerRule = compositeRule();
    }

    private PokerRule compositeRule() {
        PokerRule pokerRule = new CompositeRule();
        pokerRule.setSuccessor(new HighCardRule());
        return pokerRule;
    }

    public PokerResult play(Round playerRound, Round computerRound) {
        return pokerRule.withRule(playerRound, computerRound);
    }

    public String outputFinalResult(Round playerRound, Round computerRound, PokerResult result) {
        final StringBuilder builder = new StringBuilder();
        builder.append(playerRound.toString()).append("\n")
                .append(computerRound.toString()).append("\n\n")
                .append("Winner: ").append(result.getWinner().toString()).append("\n")
                .append(result.getWinningRound().toString()).append("\n");
        return builder.toString();
    }
}
