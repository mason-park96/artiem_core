package artiem.core.llm;

import artiem.core.engine.CheckResult;
import artiem.core.session.GameSession;
import artiem.core.world.NPCProfile;
import artiem.core.world.Location;

public class WebGptPromptRenderer {

    public static String render(GameSession session, CheckResult lastResult) {

        var world = session.getWorldState();
        var narrative = session.getNarrativeState();
        var memory = session.getMemoryState();
        var state = session.getGameState();
        var player = state.getPlayer();
        var scene = state.getScene();

        StringBuilder sb = new StringBuilder();

        sb.append("""
당신은 ‘엘더스크롤 V: 스카이림’ 세계관의 TRPG 서술자이다.
당신은 규칙을 판단하지 않으며, 이미 확정된 결과를 소설처럼 묘사한다.

[절대 규칙]
- 성공/실패를 변경하지 말 것
- 주사위, 수치, 규칙 언급 금지
- 플레이어 상태를 임의로 변경하지 말 것
- 새로운 판정이나 규칙을 만들지 말 것

""");

        // 세계관
        sb.append("[세계관]\n");
        sb.append("- 세계: ").append(world.getWorldName()).append("\n");
        sb.append("- 시대: ").append(world.getEra()).append("\n");
        sb.append("- 지역: ").append(world.getRegion()).append("\n\n");

        // 주요 장소
        sb.append("[주요 장소]\n");
        for (Location l : world.getLocations()) {
            sb.append("- ").append(l.name()).append(": ")
                    .append(l.description()).append("\n");
        }
        sb.append("\n");

        // 주요 NPC
        sb.append("[주요 인물]\n");
        for (NPCProfile npc : world.getNpcs()) {
            sb.append("- ").append(npc.name())
                    .append(" (").append(npc.faction()).append("): ")
                    .append(npc.personality())
                    .append(", 관계: ").append(npc.relationshipWithPlayer())
                    .append("\n");
        }
        sb.append("\n");

        // 스토리 진행
        sb.append("[현재 스토리]\n");
        sb.append("- 퀘스트: ").append(narrative.getCurrentQuest()).append("\n");
        sb.append("- 단계: ").append(narrative.getQuestStage()).append("\n");
        sb.append("- 분위기: ").append(narrative.getTone()).append("\n\n");

        // 최근 사건
        sb.append("[최근 사건]\n");
        for (String e : narrative.getRecentEvents()) {
            sb.append("- ").append(e).append("\n");
        }
        sb.append("\n");

        // 기억 요약
        sb.append("[세션 기억 요약]\n");
        sb.append(memory.getSessionSummary()).append("\n\n");

        // 플레이어 상태
        sb.append("[플레이어 상태]\n");
        sb.append("- HP ").append(player.getHp())
                .append("/").append(player.getHpMax()).append("\n");
        sb.append("- MP ").append(player.getMp())
                .append("/").append(player.getMpMax()).append("\n\n");

        // 현재 장면
        sb.append("[현재 장면]\n");
        sb.append("- 위치: ").append(scene.getLocation()).append("\n");
        sb.append("- 분위기: ").append(scene.getMood()).append("\n\n");

        // 이번 판정 결과
        sb.append("[이번 행동 판정 결과]\n");
        sb.append("- 상황: ").append(lastResult.getSituation()).append("\n");
        sb.append("- 결과: ").append(lastResult.isSuccess() ? "성공" : "실패").append("\n\n");

        sb.append("""
[서술 요청]
위 정보를 바탕으로,
하이판타지 소설 톤으로 장면을 묘사하라.
다음 행동을 암시할 수 있으나 선택을 강요하지 말 것.
분량은 6~10문장 이내.
""");

        return sb.toString();
    }
}
