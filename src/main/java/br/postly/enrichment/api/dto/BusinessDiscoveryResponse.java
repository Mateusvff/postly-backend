package br.postly.enrichment.api.dto;

import br.postly.enrichment.api.dto.businessdiscovery.BusinessDiscoveryRecord;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BusinessDiscoveryResponse(
        Long id,
        @JsonProperty("business_discovery") BusinessDiscoveryRecord businessDiscovery
) {
}
