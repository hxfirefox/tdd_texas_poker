package github.hxfirefox.texaspoker.game;

/**
 * Created by »ÆÏè on 15-11-7.
 */
public class PokerResultBuilder {
    private boolean isSuccess;
    private GameWinner winner;
    private Round winningRound;

    public PokerResultBuilder setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
        return this;
    }

    public PokerResultBuilder setWinner(GameWinner winner) {
        this.winner = winner;
        return this;
    }

    public PokerResultBuilder setWinningRound(Round winningRound) {
        this.winningRound = winningRound;
        return this;
    }

    public PokerResult build() {
        return new PokerResultImpl(this);
    }

    private static final class PokerResultImpl implements PokerResult {
        private boolean isSuccess;
        private GameWinner winner;
        private Round winningRound;


        public PokerResultImpl(PokerResultBuilder builder) {
            this.isSuccess = builder.isSuccess;
            this.winner = builder.winner;
            this.winningRound = builder.winningRound;
        }

        public boolean isSuccessful() {
            return isSuccess;
        }

        public GameWinner getWinner() {
            return winner;
        }

        public Round getWinningRound() {
            return winningRound;
        }
    }
}
