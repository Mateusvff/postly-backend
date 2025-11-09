package br.postly.enrichment.domain.util;

import br.postly.enrichment.api.dto.BusinessDiscoveryResponse;
import br.postly.enrichment.api.dto.businessdiscovery.MediaRecord;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Component
public class EngagementCalculator {

    private static final int TOP_POSTS_LIMIT = 10;

    public List<MediaRecord> getTopEngagedPosts(BusinessDiscoveryResponse businessDiscoveryResponse) {
        if (!isBusinessDiscoveryValid(businessDiscoveryResponse)) {
            return Collections.emptyList();
        }

        Comparator<MediaRecord> byEngagementDescending = Comparator
                .comparingInt(this::calculateEngagement)
                .reversed();

        return businessDiscoveryResponse.businessDiscovery().media().data().stream()
                .filter(Objects::nonNull)
                .sorted(byEngagementDescending)
                .limit(TOP_POSTS_LIMIT)
                .toList();
    }

    private int calculateEngagement(MediaRecord media) {
        return (media.likeCount() == null ? 0 : media.likeCount()) +
                (media.commentsCount() == null ? 0 : media.commentsCount());
    }

    private boolean isBusinessDiscoveryValid(BusinessDiscoveryResponse businessDiscoveryResponse) {
        return businessDiscoveryResponse != null &&
                businessDiscoveryResponse.businessDiscovery().media() != null &&
                businessDiscoveryResponse.businessDiscovery().media().data() != null;
    }
}
