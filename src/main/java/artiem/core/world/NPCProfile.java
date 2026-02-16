package artiem.core.world;

public record NPCProfile(
        String name,
        String faction,
        String personality,
        String status,
        String relationshipWithPlayer
) {}
