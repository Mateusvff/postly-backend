package br.postly.onboarding.domain.repository;

import br.postly.onboarding.domain.model.CreatorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatorProfileRepository extends JpaRepository<CreatorProfile, Long> {
}