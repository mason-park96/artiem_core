package artiem.core.engine;

import artiem.core.state.GameState;
import artiem.core.state.PlayerState;

public class CheckResolver {

    public static CheckResult resolve(GameState state, Situation situation) {

        PlayerState player = state.getPlayer();
        SituationRule rule = SituationRuleRegistry.getRule(situation);

        ModifierSet modifiers = new ModifierSet();

        // 주 능력치
        if (rule.getPrimary() != null) {
            int val = player.getAttribute(rule.getPrimary());
            modifiers.add(val, "주 능력치: " + rule.getPrimary());
        }

        // 보조 능력치 (절반 반영 예시)
        for (Attribute attr : rule.getSecondary()) {
            int val = player.getAttribute(attr) / 2;
            modifiers.add(val, "보조 능력치: " + attr);
        }

        // 장면 가중치
        modifiers.add(state.getScene().getTerrainModifier(), "지형");
        modifiers.add(state.getScene().getLightingModifier(), "조명");

        // 상태효과 가중치
        state.getPlayer().applyStatusModifiers(modifiers);

        int roll = Dice.d20();
        int total = roll + modifiers.getTotal();
        int dc = rule.getBaseDc();

        return new CheckResult(
                situation,
                roll,
                total,
                dc,
                total >= dc,
                modifiers
        );
    }
}
