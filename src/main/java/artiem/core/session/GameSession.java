package artiem.core.session;

import artiem.core.memory.MemoryState;
import artiem.core.narrative.NarrativeState;
import artiem.core.state.GameState;
import artiem.core.world.WorldState;

public class GameSession {

    private final GameState gameState;
    private final WorldState worldState;
    private final NarrativeState narrativeState;
    private final MemoryState memoryState;

    public GameSession(GameState gameState) {
        this.gameState = gameState;
        this.worldState = new WorldState();
        this.narrativeState = new NarrativeState();
        this.memoryState = new MemoryState();
    }

    public GameState getGameState() { return gameState; }
    public WorldState getWorldState() { return worldState; }
    public NarrativeState getNarrativeState() { return narrativeState; }
    public MemoryState getMemoryState() { return memoryState; }
}
