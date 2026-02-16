package artiem.core.engine;

public class CheckResult {

    private final Situation situation;
    private final int roll;
    private final int total;
    private final int dc;
    private final boolean success;
    private final ModifierSet modifiers;

    public CheckResult(
            Situation situation,
            int roll,
            int total,
            int dc,
            boolean success,
            ModifierSet modifiers
    ) {
        this.situation = situation;
        this.roll = roll;
        this.total = total;
        this.dc = dc;
        this.success = success;
        this.modifiers = modifiers;
    }

    public boolean isSuccess() {
        return success;
    }

    public Situation getSituation() {
        return situation;
    }

    public int getRoll() {
        return roll;
    }

    public int getTotal() {
        return total;
    }

    public int getDc() {
        return dc;
    }

    public ModifierSet getModifiers() {
        return modifiers;
    }

    public void print() {
        System.out.println("[상황: " + situation + "]");
        System.out.println("주사위: " + roll);
        System.out.println("가중치:");
        modifiers.printBreakdown();
        System.out.println("합계: " + total + " vs DC " + dc);
        System.out.println(success ? "▶ 성공" : "▶ 실패");
    }
}
