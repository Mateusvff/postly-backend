package br.postly.enrichment.domain.repository;

import br.postly.enrichment.domain.model.IgReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IgReferenceRepository extends JpaRepository<IgReference, Long> {
}
