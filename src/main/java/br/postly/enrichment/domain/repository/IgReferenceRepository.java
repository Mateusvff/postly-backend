package br.postly.enrichment.domain.repository;

import br.postly.enrichment.domain.model.IgReference;
import br.postly.onboarding.domain.model.CreatorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IgReferenceRepository extends JpaRepository<IgReference, Long> {
    List<IgReference> findAllByCreatorProfile(CreatorProfile creatorProfile);
}
