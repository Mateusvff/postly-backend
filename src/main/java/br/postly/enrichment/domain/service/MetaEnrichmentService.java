package br.postly.enrichment.domain.service;

import br.postly.enrichment.domain.exceptions.MetaPageTokenNotFoundException;
import br.postly.enrichment.domain.model.MetaPageToken;
import br.postly.enrichment.domain.repository.MetaPageTokenRepository;
import br.postly.enrichment.infrastructure.MetaGraphClient;
import br.postly.onboarding.domain.service.OnboardingService;
import br.postly.shared.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetaEnrichmentService {

    private final SubjectService subjectService;
    private final OnboardingService onboardingService;
    private final MetaGraphClient metaGraphClient;
    private final MetaPageTokenRepository metaPageTokenRepository;

    public void enrichReferences() {


        MetaPageToken metaPageToken = metaPageTokenRepository.findTopByOrderByCreatedAtDesc()
                .orElseThrow(() -> new MetaPageTokenNotFoundException("No meta page token found"));

        String pageAccessToken = metaPageToken.getPageAccessToken();
        String igUserId = metaPageToken.getIgUserId();
    }


}
