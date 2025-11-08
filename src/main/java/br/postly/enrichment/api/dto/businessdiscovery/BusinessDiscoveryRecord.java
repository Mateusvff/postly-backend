package br.postly.enrichment.api.dto.businessdiscovery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BusinessDiscoveryRecord(
        Long id,
        String name,
        String biography,
        @JsonProperty("profile_picture_url") String profilePictureUrl,
        MediaConnectionRecord media
) {
}
