package artiem.core.narrative;

public record TurnSummary(
        String quest,
        String action,
        String result,
        String consequence,
        String mood
) {

    public String toNarrativeLine() {
        return String.format(
                "[%s] %s → %s (%s)",
                quest,
                action,
                result,
                consequence
        );
    }

    public String toMemoryLine() {
        return String.format(
                "%s 결과로 %s 상황이 이어지고 있다.",
                action,
                consequence
        );
    }
}
