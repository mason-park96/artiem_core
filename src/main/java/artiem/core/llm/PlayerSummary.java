package artiem.core.llm;

public record PlayerSummary(
        int hp,
        int hpMax,
        int mp,
        int mpMax,
        String condition // 예: "출혈 상태"
) {}
