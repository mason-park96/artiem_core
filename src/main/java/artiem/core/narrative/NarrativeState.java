package artiem.core.narrative;

import java.util.ArrayList;
import java.util.List;

public class NarrativeState {

    private String currentQuest;
    private String questStage;
    private String tone;

    private final List<String> recentEvents = new ArrayList<>();
    private final List<String> unresolvedHooks = new ArrayList<>();

    public NarrativeState() {
        bootstrap();
    }

    private void bootstrap() {
        currentQuest = "헬겐 탈출";
        questStage = "처형 직전, 용의 습격";
        tone = "혼란과 공포";

        recentEvents.add("플레이어는 헬겐의 처형장에 묶여 있다.");
        unresolvedHooks.add("정체불명의 용");
        unresolvedHooks.add("스톰클록과 제국의 내전");
    }

    public String getCurrentQuest() { return currentQuest; }
    public String getQuestStage() { return questStage; }
    public String getTone() { return tone; }

    public List<String> getRecentEvents() { return recentEvents; }
    public List<String> getUnresolvedHooks() { return unresolvedHooks; }

    public void addEvent(String event) {
        recentEvents.add(event);
        if (recentEvents.size() > 5) {
            recentEvents.remove(0);
        }
    }

    public void advanceQuestStage(String nextStage) {
        this.questStage = nextStage;
    }

    public void changeTone(String tone) {
        this.tone = tone;
    }

    public void applyTurnSummary(TurnSummary summary) {
        addEvent(summary.toNarrativeLine());
        changeTone(summary.mood());
    }
}
