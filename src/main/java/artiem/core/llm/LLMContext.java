package artiem.core.llm;

public record LLMContext(
        PlayerSummary player,
        SceneSummary scene,
        CheckSummary check
) {}
