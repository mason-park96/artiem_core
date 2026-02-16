package artiem.core.engine;

import java.util.List;

public class SituationRule {

    private final Attribute primary;
    private final List<Attribute> secondary;
    private final int baseDc;

    public SituationRule(Attribute primary, List<Attribute> secondary, int baseDc) {
        this.primary = primary;
        this.secondary = secondary;
        this.baseDc = baseDc;
    }

    public Attribute getPrimary() {
        return primary;
    }

    public List<Attribute> getSecondary() {
        return secondary;
    }

    public int getBaseDc() {
        return baseDc;
    }
}
