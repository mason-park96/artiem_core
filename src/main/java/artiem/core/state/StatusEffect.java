package artiem.core.state;

import artiem.core.engine.ModifierSet;

public abstract class StatusEffect {

    protected String name;
    protected int remainingTurns;

    public StatusEffect(String name, int durationTurns) {
        this.name = name;
        this.remainingTurns = durationTurns;
    }

    public String getName() {
        return name;
    }

    public int getRemainingTurns() {
        return remainingTurns;
    }

    public boolean isExpired() {
        return remainingTurns <= 0;
    }

    /* ===== 훅 메서드 ===== */

    // 적용 시 1회
    public void onApply(GameState state) {}

    // 매 턴 종료 시
    public void onTick(GameState state) {}

    // 만료 시 1회
    public void onExpire(GameState state) {}

    // 판정 시 가중치 제공
    public void applyModifiers(ModifierSet modifiers) {}

    public void decrementTurn() {
        remainingTurns--;
    }
}
