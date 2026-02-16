package artiem.core.state;

public class SceneState {

    // 환경 가중치
    private int terrainModifier;
    private int lightingModifier; // 어두우면 -2 등

    // 분위기 (서사 전용, 수치 계산 X)
    private String mood;
    private String location;

    public SceneState(
            String location,
            String mood,
            int terrainModifier,
            int lightingModifier
    ) {
        this.location = location;
        this.mood = mood;
        this.terrainModifier = terrainModifier;
        this.lightingModifier = lightingModifier;
    }

    /* ===== 조회 ===== */

    public String getLocation() {
        return location;
    }

    public String getMood() {
        return mood;
    }

    public int getTerrainModifier() {
        return terrainModifier;
    }

    public int getLightingModifier() {
        return lightingModifier;
    }

    /* ===== 장면 변경 ===== */

    public void changeTerrainModifier(int value) {
        this.terrainModifier = value;
    }

    public void changeLightingModifier(int value) {
        this.lightingModifier = value;
    }

    public void changeMood(String mood) {
        this.mood = mood;
    }
}
