package artiem.core.engine;

public class Modifier {

    private final int value;
    private final String reason;

    public Modifier(int value, String reason) {
        this.value = value;
        this.reason = reason;
    }

    public int getValue() {
        return value;
    }

    public String getReason() {
        return reason;
    }
}
