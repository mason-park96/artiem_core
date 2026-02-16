package artiem.core.llm;

import artiem.core.engine.Situation;

public record CheckSummary(
        Situation situation,
        boolean success,
        int roll,
        int total,
        int dc
) {}
