package artiem.core.state;
import artiem.core.engine.Attribute;
import artiem.core.engine.ModifierSet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayerState {

    // 생존 자원
    private int hp;
    private int hpMax;
    private int mp;
    private int mpMax;

    // 능력치 (보정치)
    private int str;
    private int dex;
    private int con;
    private int intel;
    private int wis;
    private int cha;

    public PlayerState(
            int hpMax,
            int mpMax,
            int str,
            int dex,
            int con,
            int intel,
            int wis,
            int cha
    ) {
        this.hpMax = hpMax;
        this.hp = hpMax;
        this.mpMax = mpMax;
        this.mp = mpMax;

        this.str = str;
        this.dex = dex;
        this.con = con;
        this.intel = intel;
        this.wis = wis;
        this.cha = cha;
    }

    /* ===== 조회 ===== */

    public int getHp() { return hp; }
    public int getHpMax() { return hpMax; }
    public int getMp() { return mp; }
    public int getMpMax() { return mpMax; }

    public int getStr() { return str; }
    public int getDex() { return dex; }
    public int getCon() { return con; }
    public int getInt() { return intel; }
    public int getWis() { return wis; }
    public int getCha() { return cha; }

    /* ===== 상태 변경 (반드시 메서드로만) ===== */

    public void changeHp(int delta) {
        hp = Math.max(0, Math.min(hpMax, hp + delta));
    }

    public void changeMp(int delta) {
        mp = Math.max(0, Math.min(mpMax, mp + delta));
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int getAttribute(Attribute attr) {
        return switch (attr) {
            case STR -> str;
            case DEX -> dex;
            case CON -> con;
            case INT -> intel;
            case WIS -> wis;
            case CHA -> cha;
        };
    }



    /* ===== 상태효과 관리 ===== */
    private final List<StatusEffect> effects = new ArrayList<>();

    public void addEffect(StatusEffect effect, GameState state) {
        effects.add(effect);
        effect.onApply(state);
    }

    public void applyStatusModifiers(ModifierSet modifiers) {
        for (StatusEffect effect : effects) {
            effect.applyModifiers(modifiers);
        }
    }

    public void onTurnEnd(GameState state) {
        Iterator<StatusEffect> it = effects.iterator();

        while (it.hasNext()) {
            StatusEffect effect = it.next();
            effect.onTick(state);
            effect.decrementTurn();

            if (effect.isExpired()) {
                effect.onExpire(state);
                System.out.println("[상태 종료] " + effect.getName());
                it.remove();
            }
        }
    }

}

