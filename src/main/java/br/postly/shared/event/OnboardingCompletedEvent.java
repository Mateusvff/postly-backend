package br.postly.shared.event;

import br.postly.onboarding.domain.model.CreatorProfile;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OnboardingCompletedEvent extends ApplicationEvent {

    private final CreatorProfile creatorProfile;

    public OnboardingCompletedEvent(CreatorProfile creatorProfile) {
        super(creatorProfile);
        this.creatorProfile = creatorProfile;
    }
}


