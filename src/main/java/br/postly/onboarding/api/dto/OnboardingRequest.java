package br.postly.onboarding.api.dto;

import br.postly.onboarding.domain.enums.MainGoal;
import br.postly.onboarding.domain.enums.OnboardingStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record OnboardingRequest(

        @NotBlank @Max(30) String igUsername,
        @NotBlank @Max(50) String niche,
        @NotNull MainGoal mainGoal,
        @NotNull Integer postPerWeek,
        @NotNull OnboardingStatus status,
        @NotNull Set<String> igReferences,
        @Max(255) String comments

) {
}
