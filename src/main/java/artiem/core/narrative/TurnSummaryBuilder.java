package artiem.core.narrative;

import artiem.core.engine.CheckResult;

public class TurnSummaryBuilder {

    public static TurnSummary build(
            NarrativeState narrative,
            CheckResult result,
            String playerIntent
    ) {

        String quest = narrative.getCurrentQuest();
        String action = playerIntent;
        String outcome = result.isSuccess() ? "성공" : "실패";

        String consequence = switch (result.getSituation()) {
            case COMBAT -> result.isSuccess()
                    ? "적이 밀려나며 전황이 유리해졌다"
                    : "전황이 불리해졌다";
            case STEALTH -> result.isSuccess()
                    ? "눈에 띄지 않고 움직였다"
                    : "기척이 드러났다";
            case DIALOGUE -> result.isSuccess()
                    ? "상대의 반응이 누그러졌다"
                    : "상대가 경계심을 보였다";
            default -> "상황이 변화했다";
        };

        String mood = result.isSuccess()
                ? "긴장 속의 기회"
                : "불안과 압박";

        return new TurnSummary(
                quest,
                action,
                outcome,
                consequence,
                mood
        );
    }
}
