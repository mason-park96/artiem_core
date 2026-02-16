package artiem.core.llm;

public class DummyLLMClient implements LLMClient {

    @Override
    public String generate(String prompt) {
        return """
당신의 행동은 장면에 즉각적인 변화를 일으킨다.
결과는 당신이 선택한 행동의 필연적인 귀결로 드러난다.
아르티엠의 세계는 당신의 다음 결정을 기다리고 있다.
""";
    }
}
