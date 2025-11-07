package br.postly.onboarding.repository;

import br.postly.onboarding.model.CreatorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatorProfileRepository extends JpaRepository<CreatorProfile, Long> {
}