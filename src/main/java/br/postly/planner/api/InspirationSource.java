package br.postly.planner.api;

import java.util.List;

public record InspirationSource(
        String username,
        String biography,
        List<TopPostInsight> topPosts
) {
}
