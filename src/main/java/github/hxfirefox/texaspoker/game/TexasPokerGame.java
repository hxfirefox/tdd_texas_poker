package github.hxfirefox.texaspoker.game;

import github.hxfirefox.texaspoker.rule.*;

import static github.hxfirefox.texaspoker.game.GameWinner.*;

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
        StringBuilder builder = outputBothSidesRound(playerRound, computerRound);
        outputWinner(result, builder);
        outputWinnerRound(result, builder);

        return builder.toString();
    }

    private void outputWinnerRound(PokerResult result, StringBuilder builder) {
        builder.append("\n")
                .append(result.getWinningRound().toString())
                .append("\n");
    }

    private void outputWinner(PokerResult result, StringBuilder builder) {
        builder.append("Winner: ").append(result.getWinner().toString());
        if (isWinnerPlayer(result)) {
            builder.append(" ").append(playerName);
        }
    }

    private StringBuilder outputBothSidesRound(Round playerRound, Round computerRound) {
        final StringBuilder builder = new StringBuilder()
                .append(playerRound.toString()).append("\n")
                .append(computerRound.toString()).append("\n\n");
        return builder;
    }

    private boolean isWinnerPlayer(PokerResult result) {
        return result.getWinner() == PLAYER;
    }
}
