package br.postly.planner.api;

public record CreatorProfileBriefing(
        String username,
        String niche,
        String mainGoal,
        Integer postPerWeek,
        String comments
) {
}
