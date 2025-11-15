package br.postly.planner.api;

import java.util.List;

public record ContentGenerationBriefing(
        CreatorProfileBriefing profile,
        List<InspirationSource> references
) {
}
