package br.postly.enrichment.domain.service;

import br.postly.enrichment.api.dto.BusinessDiscoveryResponse;
import br.postly.enrichment.api.dto.businessdiscovery.MediaRecord;
import br.postly.enrichment.domain.enums.MetaTemplate;
import br.postly.enrichment.domain.exceptions.MetaPageTokenNotFoundException;
import br.postly.enrichment.domain.model.IgReference;
import br.postly.enrichment.domain.model.IgReferenceMediaContent;
import br.postly.enrichment.domain.model.MetaPageToken;
import br.postly.enrichment.domain.repository.IgReferenceMediaContentRepository;
import br.postly.enrichment.domain.repository.IgReferenceRepository;
import br.postly.enrichment.domain.repository.MetaPageTokenRepository;
import br.postly.enrichment.infrastructure.MetaGraphClient;
import br.postly.onboarding.domain.service.OnboardingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MetaEnrichmentService {

    @Value("${meta.ig.user.id}")
    private String igUserId;

    private final MetaGraphClient metaGraphClient;
    private final OnboardingService onboardingService;

    private final MetaPageTokenRepository metaPageTokenRepository;
    private final IgReferenceRepository igReferenceRepository;
    private final IgReferenceMediaContentRepository igReferenceMediaContentRepository;

    public void enrichReferences() {
        String pageAccessToken = retrievePageAccessToken();
        Set<String> userIgReferences = onboardingService.getUserIgReferences();

        for (String igReferenceUsername : userIgReferences) {
            processIgReference(igReferenceUsername, pageAccessToken);
        }
    }

    private void processIgReference(String igReferenceUsername, String pageAccessToken) {
        String fields = String.format(MetaTemplate.BUSINESS_DISCOVERY_FIELDS_TEMPLATE.getTemplate(), igReferenceUsername);
        BusinessDiscoveryResponse businessDiscoveryResponse = metaGraphClient.getBusinessDiscovery(igUserId, fields, pageAccessToken);

        List<MediaRecord> topEngagedPosts = getTopEngagedPosts(businessDiscoveryResponse);

        IgReference igReference = createIgReferenceFromResponse(businessDiscoveryResponse);
        igReferenceRepository.save(igReference);

        saveMediaContents(topEngagedPosts, igReference);
    }

    private IgReference createIgReferenceFromResponse(BusinessDiscoveryResponse response) {
        IgReference igReference = new IgReference();
        igReference.setUsername(response.businessDiscovery().name());
        igReference.setBiography(response.businessDiscovery().biography());
        igReference.setProfilePictureUrl(response.businessDiscovery().profilePictureUrl());
        return igReference;
    }

    private void saveMediaContents(List<MediaRecord> mediaRecords, IgReference igReference) {
        mediaRecords.forEach(mediaRecord -> {
            IgReferenceMediaContent mediaContent = createMediaContentFromRecord(mediaRecord, igReference);
            igReferenceMediaContentRepository.save(mediaContent);
        });
    }

    private IgReferenceMediaContent createMediaContentFromRecord(MediaRecord mediaRecord, IgReference igReference) {
        IgReferenceMediaContent mediaContent = new IgReferenceMediaContent();
        mediaContent.setIgReference(igReference);
        mediaContent.setCaption(mediaRecord.caption());
        mediaContent.setMediaUrl(mediaRecord.mediaUrl());
        mediaContent.setMediaType(mediaRecord.mediaType());
        mediaContent.setMediaProductType(mediaRecord.mediaProductType());
        mediaContent.setLikeCount(mediaRecord.likeCount());
        mediaContent.setCommentsCount(mediaRecord.commentsCount());
        return mediaContent;
    }

    private String retrievePageAccessToken() {
        MetaPageToken metaPageToken = metaPageTokenRepository.findTopByOrderByCreatedAtDesc()
                .orElseThrow(() -> new MetaPageTokenNotFoundException("No meta page token found"));
        return metaPageToken.getPageAccessToken();
    }

    private List<MediaRecord> getTopEngagedPosts(BusinessDiscoveryResponse businessDiscoveryResponse) {
        if (!isBusinessDiscoveryValid(businessDiscoveryResponse)) {
            return Collections.emptyList();
        }

        Comparator<MediaRecord> byEngagementDescending = Comparator
                .comparingInt(this::calculateEngagement)
                .reversed();

        return businessDiscoveryResponse.businessDiscovery().media().data().stream()
                .filter(Objects::nonNull)
                .sorted(byEngagementDescending)
                .limit(10)
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
