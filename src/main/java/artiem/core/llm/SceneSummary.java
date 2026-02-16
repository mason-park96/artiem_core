package artiem.core.llm;

public record SceneSummary(
        String location,
        String mood,
        boolean dark,
        boolean narrow
) {}
