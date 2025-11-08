package br.postly.enrichment.api.dto.businessdiscovery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;



@JsonIgnoreProperties(ignoreUnknown = true)
public record MediaRecord(
        Long id,
        String caption,
        @JsonProperty("media_url") String mediaUrl,
        @JsonProperty("media_type") String mediaType,
        @JsonProperty("media_product_type") String mediaProductType,
        @JsonProperty("like_count") Integer likeCount,
        @JsonProperty("comments_count") Integer commentsCount
) {
}
