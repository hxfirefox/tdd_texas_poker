package github.hxfirefox.texaspoker.game;

/**
 * Created by »ÆÏè on 15-11-6.
 */
public interface PokerResult {
    public boolean isSuccessful();
    public GameWinner getWinner();
    public Round getWinningRound();
}
