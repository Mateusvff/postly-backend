package br.postly.onboarding.domain.service;

import br.postly.auth.domain.model.User;
import br.postly.enrichment.domain.service.MetaEnrichmentService;
import br.postly.onboarding.api.dto.OnboardingRequest;
import br.postly.onboarding.domain.enums.OnboardingStatus;
import br.postly.onboarding.domain.model.CreatorProfile;
import br.postly.onboarding.domain.repository.CreatorProfileRepository;
import br.postly.shared.service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OnboardingService {

    private final SubjectService subjectService;
    private final CreatorProfileRepository creatorProfileRepository;
    private final MetaEnrichmentService metaEnrichmentService;

    public void onboardCreator(OnboardingRequest request) {
        User user = subjectService.getCurrentUser();

        log.info("Onboarding creator with id: {}", user.getId());
        CreatorProfile creatorProfile = new CreatorProfile();
        creatorProfile.setUser(user);
        creatorProfile.setIgUsername(request.igUsername());
        creatorProfile.setNiche(request.niche());
        creatorProfile.setMainGoal(request.mainGoal());
        creatorProfile.setIgReferences(request.igReferences());
        creatorProfile.setStatus(OnboardingStatus.PROCESSING);
        creatorProfile.setPostPerWeek(request.postPerWeek());
        creatorProfile.setComments(request.comments());

        CreatorProfile savedCreator = creatorProfileRepository.save(creatorProfile);
        metaEnrichmentService.enrichReferences(savedCreator);
    }

}
