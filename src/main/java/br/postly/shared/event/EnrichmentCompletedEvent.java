package br.postly.shared.event;

import br.postly.onboarding.domain.model.CreatorProfile;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EnrichmentCompletedEvent extends ApplicationEvent {

    private final CreatorProfile creatorProfile;

    public EnrichmentCompletedEvent(CreatorProfile creatorProfile) {
        super(creatorProfile);
        this.creatorProfile = creatorProfile;
    }
}
