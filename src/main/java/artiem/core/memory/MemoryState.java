package artiem.core.memory;

public class MemoryState {

    private String sessionSummary;
    private String characterProfile;

    public MemoryState() {
        sessionSummary =
                "플레이어는 헬겐에서 처형을 앞두고 있었으나, " +
                        "용의 습격으로 혼란이 발생했다.";

        characterProfile =
                "플레이어는 생존을 우선하며, " +
                        "상황을 신중히 관찰하는 성향이다.";
    }

    public String getSessionSummary() {
        return sessionSummary;
    }

    public String getCharacterProfile() {
        return characterProfile;
    }

    public void updateSessionSummary(String summary) {
        this.sessionSummary = summary;
    }

    public void updateCharacterProfile(String profile) {
        this.characterProfile = profile;
    }
}
