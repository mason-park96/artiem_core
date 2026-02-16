package artiem.core.engine;

import artiem.core.engine.CheckResolver;
import artiem.core.engine.CheckResult;
import artiem.core.llm.WebGptPromptRenderer;
import artiem.core.narrative.TurnSummary;
import artiem.core.narrative.TurnSummaryBuilder;
import artiem.core.memory.MemoryStateUpdater;
import artiem.core.session.GameSession;
import artiem.core.state.GameState;
import artiem.core.state.PlayerState;
import artiem.core.state.SceneState;
import artiem.core.state.effects.BleedingEffect;
import artiem.core.state.effects.FocusEffect;

public class GameEngine {

    private final GameSession session;

    public GameEngine() {

        PlayerState player = new PlayerState(
                25,   // HP_MAX
                10,   // MP_MAX
                3,    // STR
                2,    // DEX
                1,    // CON
                0,    // INT
                0,    // WIS
                1     // CHA
        );

        SceneState scene = new SceneState(
                "헬겐",
                "혼란과 공포",
                -1, // 지형
                -2  // 조명
        );

        GameState gameState = new GameState(player, scene);
        this.session = new GameSession(gameState);
    }

    /* =========================
       입력 처리
       ========================= */

    public void handleInput(String input) {

        String trimmed = input.trim();

        if (trimmed.equalsIgnoreCase("status")) {
            printStatus();
            return;
        }

        if (trimmed.toLowerCase().startsWith("attack")) {
            runCheck(Situation.COMBAT,
                    extractIntent(trimmed, "정면에서 공격한다"));
            return;
        }

        if (trimmed.toLowerCase().startsWith("stealth")) {
            runCheck(Situation.STEALTH,
                    extractIntent(trimmed, "주변을 살피며 은밀히 이동한다"));
            return;
        }

        if (trimmed.toLowerCase().startsWith("talk")) {
            runCheck(Situation.DIALOGUE,
                    extractIntent(trimmed, "상대에게 말을 건다"));
            return;
        }

        if (trimmed.equalsIgnoreCase("buff focus")) {
            session.getGameState().getPlayer()
                    .addEffect(new FocusEffect(2), session.getGameState());
            return;
        }

        if (trimmed.equalsIgnoreCase("debuff bleed")) {
            session.getGameState().getPlayer()
                    .addEffect(new BleedingEffect(3), session.getGameState());
            return;
        }

        if (trimmed.equalsIgnoreCase("turn")) {
            endTurn();
            return;
        }

        System.out.println(
                "명령: status | attack <의도> | stealth <의도> | talk <의도> | buff focus | debuff bleed | turn | exit"
        );
    }

    /* =========================
       핵심 턴 처리
       ========================= */

    private void runCheck(Situation situation, String intent) {

        GameState state = session.getGameState();

        // 1. 판정
        CheckResult result = CheckResolver.resolve(state, situation);
        result.print();

        // 2. 턴 요약 생성
        TurnSummary summary = TurnSummaryBuilder.build(
                session.getNarrativeState(),
                result,
                intent
        );

        // 3. 서사 상태 반영
        session.getNarrativeState().applyTurnSummary(summary);

        // 4. 기억 상태 반영
        MemoryStateUpdater.update(session.getMemoryState(), summary);

        // 5. 디버그 출력
        System.out.println("\n[턴 요약]");
        System.out.println(summary.toNarrativeLine());

        String webPrompt = WebGptPromptRenderer.render(session, result);

        System.out.println("\n[Web GPT 프롬프트]");
        System.out.println(webPrompt);
    }

    /* =========================
       상태 출력
       ========================= */

    private void printStatus() {

        GameState state = session.getGameState();

        System.out.println("[턴] " + state.getTurn());
        System.out.println("[장면] "
                + state.getScene().getLocation()
                + " / "
                + state.getScene().getMood());

        System.out.println("[플레이어]");
        System.out.println("HP: "
                + state.getPlayer().getHp()
                + "/"
                + state.getPlayer().getHpMax());
        System.out.println("MP: "
                + state.getPlayer().getMp()
                + "/"
                + state.getPlayer().getMpMax());

        System.out.println("\n[최근 사건]");
        session.getNarrativeState()
                .getRecentEvents()
                .forEach(e -> System.out.println("- " + e));
    }

    /* =========================
       턴 종료
       ========================= */

    private void endTurn() {
        session.getGameState().endTurn();
        System.out.println("[턴 종료] 다음 턴: " + session.getGameState().getTurn());
    }

    /* =========================
       유틸
       ========================= */

    private String extractIntent(String input, String defaultIntent) {
        String[] parts = input.split("\\s+", 2);
        if (parts.length < 2 || parts[1].isBlank()) {
            return defaultIntent;
        }
        return parts[1].trim();
    }
}
