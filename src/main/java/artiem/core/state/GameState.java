package artiem.core.state;

public class GameState {

    private final PlayerState player;
    private final SceneState scene;

    private int turn = 1;

    public GameState(PlayerState player, SceneState scene) {
        this.player = player;
        this.scene = scene;
    }

    public PlayerState getPlayer() {
        return player;
    }

    public SceneState getScene() {
        return scene;
    }

    public int getTurn() {
        return turn;
    }

    public void endTurn() {
        player.onTurnEnd(this);
        turn++;
    }
}
