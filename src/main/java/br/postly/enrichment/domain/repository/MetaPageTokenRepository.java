package br.postly.enrichment.domain.repository;

import br.postly.enrichment.domain.model.MetaPageToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MetaPageTokenRepository extends JpaRepository<MetaPageToken, Long> {

    Optional<MetaPageToken> findTopByOrderByCreatedAtDesc();
}
