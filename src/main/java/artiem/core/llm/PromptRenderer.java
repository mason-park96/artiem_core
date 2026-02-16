package artiem.core.llm;

public class PromptRenderer {

    public static String render(LLMContext ctx) {

        PlayerSummary p = ctx.player();
        SceneSummary s = ctx.scene();
        CheckSummary c = ctx.check();

        return """
당신은 하이판타지 세계 ‘아르티엠’의 TRPG 서술자이다.
아래 판정 결과는 이미 확정된 사실이며 변경할 수 없다.

[판정 결과]
- 상황: %s
- 성공 여부: %s
- 주사위: %d
- 총합: %d / DC %d

[플레이어 상태]
- HP: %d / %d
- MP: %d / %d

[장면]
- 장소: %s
- 분위기: %s

규칙:
1. 성공/실패를 바꾸지 말 것
2. 수치나 규칙을 직접 언급하지 말 것
3. 소설처럼 묘사할 것
4. 결과에 맞는 자연스러운 전개만 서술할 것

서술:
""".formatted(
                c.situation(),
                c.success() ? "성공" : "실패",
                c.roll(),
                c.total(),
                c.dc(),
                p.hp(),
                p.hpMax(),
                p.mp(),
                p.mpMax(),
                s.location(),
                s.mood()
        );
    }

    public static String renderForWebGPT(LLMContext ctx) {

        return """
[Artiem TRPG – 서사 요청]

[판정 결과]
- 상황: %s
- 결과: %s

[플레이어 상태]
- HP: %d/%d
- MP: %d/%d
- 특이 상태: 정상

[장면 정보]
- 장소: %s
- 분위기: %s

요청:
위 정보를 바탕으로,
규칙이나 수치를 언급하지 말고
하이판타지 소설 톤으로 장면을 묘사하라.
""".formatted(
                ctx.check().situation(),
                ctx.check().success() ? "성공" : "실패",
                ctx.player().hp(),
                ctx.player().hpMax(),
                ctx.player().mp(),
                ctx.player().mpMax(),
                ctx.scene().location(),
                ctx.scene().mood()
        );
    }
}
