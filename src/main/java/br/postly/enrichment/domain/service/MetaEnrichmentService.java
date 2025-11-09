package br.postly.enrichment.domain.service;

import br.postly.enrichment.api.dto.BusinessDiscoveryResponse;
import br.postly.enrichment.api.dto.businessdiscovery.MediaRecord;
import br.postly.enrichment.domain.enums.MetaTemplate;
import br.postly.enrichment.domain.exceptions.MetaPageTokenNotFoundException;
import br.postly.enrichment.domain.exceptions.ProcessReferencesException;
import br.postly.enrichment.domain.model.IgReference;
import br.postly.enrichment.domain.model.IgReferenceMediaContent;
import br.postly.enrichment.domain.model.MetaPageToken;
import br.postly.enrichment.domain.repository.IgReferenceMediaContentRepository;
import br.postly.enrichment.domain.repository.IgReferenceRepository;
import br.postly.enrichment.domain.repository.MetaPageTokenRepository;
import br.postly.enrichment.infrastructure.MetaGraphApiClient;
import br.postly.onboarding.domain.enums.OnboardingStatus;
import br.postly.enrichment.domain.mapper.IgReferenceMapper;
import br.postly.enrichment.domain.mapper.MediaContentMapper;
import br.postly.onboarding.domain.model.CreatorProfile;
import br.postly.onboarding.domain.repository.CreatorProfileRepository;
import br.postly.enrichment.domain.util.EngagementCalculator;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetaEnrichmentService {

    @Value("${meta.ig.user.id}")
    private String igUserId;

    private final MetaGraphApiClient metaGraphClient;
    private final EngagementCalculator engagementCalculator;

    private final IgReferenceMapper igReferenceMapper;
    private final MediaContentMapper mediaContentMapper;

    private final IgReferenceRepository igReferenceRepository;
    private final MetaPageTokenRepository metaPageTokenRepository;
    private final CreatorProfileRepository creatorProfileRepository;
    private final IgReferenceMediaContentRepository igReferenceMediaContentRepository;

    @Async
    public void enrichReferences(CreatorProfile creatorProfile) {
        try {
            String pageAccessToken = retrievePageAccessToken();

            for (String igReferenceUsername : creatorProfile.getIgReferences()) {
                processIgReference(creatorProfile, igReferenceUsername, pageAccessToken);
            }
            creatorProfile.setStatus(OnboardingStatus.COMPLETED);
        } catch (Exception e) {
            creatorProfile.setStatus(OnboardingStatus.FAILED);
        } finally {
            creatorProfileRepository.save(creatorProfile);
        }
    }

    private void processIgReference(CreatorProfile creatorProfile, String igReferenceUsername, String pageAccessToken) throws ProcessReferencesException {
        try {
            String fields = String.format(MetaTemplate.BUSINESS_DISCOVERY_FIELDS_TEMPLATE.getTemplate(), igReferenceUsername);
            BusinessDiscoveryResponse businessDiscoveryResponse = metaGraphClient.getBusinessDiscovery(igUserId, fields, pageAccessToken);

            List<MediaRecord> topEngagedPosts = engagementCalculator.getTopEngagedPosts(businessDiscoveryResponse);

            IgReference igReference = igReferenceMapper.mapToIgReference(businessDiscoveryResponse);
            igReference.setCreatorProfile(creatorProfile);
            igReferenceRepository.save(igReference);

            saveMediaContents(topEngagedPosts, igReference);
        } catch (FeignException.FeignClientException e) {
            throw new ProcessReferencesException("Error processing IG reference: " + igReferenceUsername + ". Reason: " + e.getMessage());
        }

    }

    private void saveMediaContents(List<MediaRecord> mediaRecords, IgReference igReference) {
        mediaRecords.forEach(mediaRecord -> {
            IgReferenceMediaContent mediaContent = mediaContentMapper.mapToMediaContent(mediaRecord, igReference);
            igReferenceMediaContentRepository.save(mediaContent);
        });
    }

    private String retrievePageAccessToken() {
        MetaPageToken metaPageToken = metaPageTokenRepository.findTopByOrderByCreatedAtDesc()
                .orElseThrow(() -> new MetaPageTokenNotFoundException("No meta page token found"));
        return metaPageToken.getPageAccessToken();
    }

}
