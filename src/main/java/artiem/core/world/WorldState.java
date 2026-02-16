package artiem.core.world;

import java.util.ArrayList;
import java.util.List;

public class WorldState {

    private final String worldName = "The Elder Scrolls: Skyrim";
    private final String era = "4E 201";
    private final String region = "Skyrim";

    private final List<Faction> factions = new ArrayList<>();
    private final List<Location> locations = new ArrayList<>();
    private final List<NPCProfile> npcs = new ArrayList<>();

    public WorldState() {
        bootstrap();
    }

    private void bootstrap() {
        // 세력
        factions.add(new Faction("제국군", "질서와 통치를 중시"));
        factions.add(new Faction("스톰클록", "노르드의 독립을 추구"));
        factions.add(new Faction("탈모어", "엘프 우월주의"));

        // 장소
        locations.add(new Location("헬겐", "제국의 처형장이 있는 마을"));
        locations.add(new Location("리버우드", "작은 목재 마을"));
        locations.add(new Location("화이트런", "중앙 평원에 위치한 도시"));

        // 주요 NPC
        npcs.add(new NPCProfile(
                "랄로프",
                "스톰클록",
                "직설적이며 충성심이 강함",
                "생존",
                "중립"
        ));
    }

    public String getWorldName() { return worldName; }
    public String getEra() { return era; }
    public String getRegion() { return region; }

    public List<Faction> getFactions() { return factions; }
    public List<Location> getLocations() { return locations; }
    public List<NPCProfile> getNpcs() { return npcs; }
}
