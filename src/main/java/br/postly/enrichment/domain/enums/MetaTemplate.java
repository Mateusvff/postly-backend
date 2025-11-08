package br.postly.enrichment.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MetaTemplate {

    BUSINESS_DISCOVERY_FIELDS_TEMPLATE("business_discovery.username(%s){username,name,biography,profile_picture_url,followers_count,media_count,media.limit(50){caption,media_url,media_type,media_product_type,like_count,comments_count}}");

    private final String template;
}
