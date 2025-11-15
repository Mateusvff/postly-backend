package br.postly.planner.domain;

import br.postly.enrichment.domain.model.IgReference;
import br.postly.enrichment.domain.repository.IgReferenceRepository;
import br.postly.onboarding.domain.model.CreatorProfile;
import br.postly.planner.api.ContentGenerationBriefing;
import br.postly.planner.api.CreatorProfileBriefing;
import br.postly.planner.api.InspirationSource;
import br.postly.planner.api.TopPostInsight;
import br.postly.shared.event.EnrichmentCompletedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlannerService {

    private final IgReferenceRepository igReferenceRepository;

    @Async
    @EventListener
    public void generatePlanner(EnrichmentCompletedEvent event) throws JsonProcessingException {
        CreatorProfile creatorProfile = event.getCreatorProfile();
        log.info("Handling planning for profile id: {}", creatorProfile.getId());

        List<IgReference> igReferences = igReferenceRepository.findAllByCreatorProfile(creatorProfile);

        List<InspirationSource> references = igReferences.stream().map(ref -> {
            List<TopPostInsight> topPosts = ref.getMediaContents().stream()
                    .map(media -> new TopPostInsight(
                            media.getCaption(),
                            media.getMediaType(),
                            media.getMediaProductType()
                    ))
                    .toList();

            return new InspirationSource(
                    ref.getUsername(),
                    ref.getBiography(),
                    topPosts
            );
        }).toList();

        CreatorProfileBriefing profile = new CreatorProfileBriefing(
                creatorProfile.getIgUsername(),
                creatorProfile.getNiche(),
                creatorProfile.getMainGoal().getDescription(),
                creatorProfile.getPostPerWeek(),
                creatorProfile.getComments()
        );

        ContentGenerationBriefing contentGenerationBriefing = new ContentGenerationBriefing(
                profile, references
        );

        log.info("JSON: {}", creatorProfile.getId());
        System.out.println(new ObjectMapper().writeValueAsString(contentGenerationBriefing));
    }
}
