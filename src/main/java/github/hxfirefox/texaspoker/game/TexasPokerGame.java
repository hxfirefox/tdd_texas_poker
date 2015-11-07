package github.hxfirefox.texaspoker.game;

import github.hxfirefox.texaspoker.rule.*;

import static github.hxfirefox.texaspoker.game.GameWinner.*;

/**
 * Created by »ÆÏè on 15-11-7.
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
        final FlushRule flushRule = new FlushRule();
        final OnePairRule onePairRule = new OnePairRule();
        final HighCardRule highCardRule = new HighCardRule();

        pokerRule.setSuccessor(flushRule);
        flushRule.setSuccessor(onePairRule);
        onePairRule.setSuccessor(highCardRule);

        return pokerRule;
    }

    public PokerResult play(Round playerRound, Round computerRound) {
        return pokerRule.withRule(playerRound, computerRound);
    }

    public String outputFinalResult(Round playerRound, Round computerRound, PokerResult result) {
        final StringBuilder builder = new StringBuilder();
        builder.append(playerRound.toString()).append("\n")
                .append(computerRound.toString()).append("\n\n")
                .append("Winner: ").append(result.getWinner().toString());
        if (result.getWinner() == PLAYER) {
            builder.append(" ").append(playerName);
        }
        builder.append("\n")
                .append(result.getWinningRound().toString()).append("\n");
        return builder.toString();
    }
}
