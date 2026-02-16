package artiem.core.memory;

import artiem.core.narrative.TurnSummary;

public class MemoryStateUpdater {

    public static void update(MemoryState memory, TurnSummary summary) {

        String newSummary =
                memory.getSessionSummary() + " " +
                        summary.toMemoryLine();

        memory.updateSessionSummary(newSummary);
    }
}
