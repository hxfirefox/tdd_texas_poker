package github.hxfirefox.texaspoker.game;

public interface PokerResult {
    public boolean isSuccessful();
    public GameWinner getWinner();
    public Round getWinningRound();
}
