package artiem.core.llm;

import artiem.core.engine.CheckResult;
import artiem.core.state.GameState;

public class LLMContextBuilder {

    public static LLMContext build(GameState state, CheckResult result) {

        PlayerSummary player = new PlayerSummary(
                state.getPlayer().getHp(),
                state.getPlayer().getHpMax(),
                state.getPlayer().getMp(),
                state.getPlayer().getMpMax(),
                "정상"
        );

        SceneSummary scene = new SceneSummary(
                state.getScene().getLocation(),
                state.getScene().getMood(),
                state.getScene().getLightingModifier() < 0,
                state.getScene().getTerrainModifier() < 0
        );

        CheckSummary check = new CheckSummary(
                result.getSituation(),
                result.isSuccess(),
                result.getRoll(),
                result.getTotal(),
                result.getDc()
        );

        return new LLMContext(player, scene, check);
    }
}
