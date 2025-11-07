package br.postly.onboarding.service;

import br.postly.auth.model.User;
import br.postly.common.service.SubjectService;
import br.postly.onboarding.dto.OnboardingRequest;
import br.postly.onboarding.model.CreatorProfile;
import br.postly.onboarding.repository.CreatorProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OnboardingService {

    private final SubjectService subjectService;
    private final CreatorProfileRepository creatorProfileRepository;

    public void onboardCreator(OnboardingRequest request) {
        User user = subjectService.getCurrentUser();

        log.info("Onboarding creator with id: {}", user.getId());
        CreatorProfile creatorProfile = new CreatorProfile();
        creatorProfile.setUser(user);
        creatorProfile.setIgUsername(request.igUsername());
        creatorProfile.setNiche(request.niche());
        creatorProfile.setMainGoal(request.mainGoal());
        creatorProfile.setIgReferences(request.igReferences());
        creatorProfile.setStatus(request.status());
        creatorProfile.setPostPerWeek(request.postPerWeek());
        creatorProfile.setComments(request.comments());

        creatorProfileRepository.save(creatorProfile);
    }

}
