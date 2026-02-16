package artiem.core.state.effects;

import artiem.core.engine.ModifierSet;
import artiem.core.state.StatusEffect;

public class FocusEffect extends StatusEffect {

    public FocusEffect(int duration) {
        super("집중", duration);
    }

    @Override
    public void applyModifiers(ModifierSet modifiers) {
        modifiers.add(2, "집중");
    }
}
