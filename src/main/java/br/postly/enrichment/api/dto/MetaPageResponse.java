package br.postly.enrichment.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MetaPageResponse(List<PageData> data, Paging paging) {
    public record PageData(
            @JsonProperty("access_token") String accessToken,
            @JsonProperty("category_list") List<CategoryItem> categoryList,
            String category,
            String name,
            String id,
            List<String> tasks
    ) {
    }

    public record CategoryItem(String id, String name) {
    }

    public record Paging(Cursors cursors) {
    }

    public record Cursors(String before, String after) {
    }
}