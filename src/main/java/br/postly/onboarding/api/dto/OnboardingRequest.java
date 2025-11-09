package br.postly.onboarding.api.dto;

import br.postly.onboarding.domain.enums.MainGoal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record OnboardingRequest(
        @NotBlank @Size(max = 30) String igUsername,
        @NotBlank @Size(max = 50) String niche,
        @NotNull MainGoal mainGoal,
        @NotNull Integer postPerWeek,
        @NotNull Set<String> igReferences,
        @Size(max = 255) String comments
) {
}
