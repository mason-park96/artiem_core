package artiem.core.state.effects;

import artiem.core.state.GameState;
import artiem.core.state.StatusEffect;

public class BleedingEffect extends StatusEffect {

    public BleedingEffect(int duration) {
        super("출혈", duration);
    }

    @Override
    public void onTick(GameState state) {
        state.getPlayer().changeHp(-1);
        System.out.println("[상태] 출혈로 HP -1");
    }
}
