package artiem.core.engine;

import java.util.ArrayList;
import java.util.List;

public class ModifierSet {

    private final List<Modifier> modifiers = new ArrayList<>();

    public void add(int value, String reason) {
        modifiers.add(new Modifier(value, reason));
    }

    public int getTotal() {
        return modifiers.stream()
                .mapToInt(Modifier::getValue)
                .sum();
    }

    public void printBreakdown() {
        for (Modifier m : modifiers) {
            System.out.printf("  %+d (%s)%n", m.getValue(), m.getReason());
        }
    }
}
