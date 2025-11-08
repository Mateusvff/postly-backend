package br.postly.enrichment.domain.repository;

import br.postly.enrichment.domain.model.IgReferenceMediaContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IgReferenceMediaContentRepository extends JpaRepository<IgReferenceMediaContent, Long> {
}
