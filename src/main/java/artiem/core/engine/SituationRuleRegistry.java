package artiem.core.engine;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static artiem.core.engine.Attribute.*;

public class SituationRuleRegistry {

    private static final Map<Situation, SituationRule> RULES =
            new EnumMap<>(Situation.class);

    static {
        RULES.put(Situation.COMBAT,
                new SituationRule(STR, List.of(DEX), 12));

        RULES.put(Situation.STEALTH,
                new SituationRule(DEX, List.of(), 12));

        RULES.put(Situation.ASSASSINATION,
                new SituationRule(DEX, List.of(STR), 15));

        RULES.put(Situation.DIALOGUE,
                new SituationRule(CHA, List.of(WIS), 10));

        RULES.put(Situation.PERSUASION,
                new SituationRule(CHA, List.of(INT), 12));

        RULES.put(Situation.INTIMIDATION,
                new SituationRule(CHA, List.of(STR), 12));

        RULES.put(Situation.TRADE,
                new SituationRule(CHA, List.of(INT), 10));

        RULES.put(Situation.INVESTIGATION,
                new SituationRule(INT, List.of(WIS), 12));

        RULES.put(Situation.TRAVEL,
                new SituationRule(CON, List.of(DEX), 10));

        RULES.put(Situation.FREE,
                new SituationRule(null, List.of(), 8));
    }

    public static SituationRule getRule(Situation situation) {
        SituationRule rule = RULES.get(situation);
        if (rule == null) {
            throw new IllegalArgumentException("No rule for situation: " + situation);
        }
        return rule;
    }
}
