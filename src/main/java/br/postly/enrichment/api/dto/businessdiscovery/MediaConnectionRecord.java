package br.postly.enrichment.api.dto.businessdiscovery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MediaConnectionRecord(List<MediaRecord> data) {
}
