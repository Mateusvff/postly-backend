package br.postly.onboarding.domain.enums;

import lombok.Getter;

@Getter
public enum OnboardingStatus {
    PROCESSING,
    ENRICHMENT_COMPLETED,
    ENRICHMENT_FAILED,
    PLANNING_IN_PROGRESS,
    PLANNING_COMPLETED,
    PLANNING_FAILED
}