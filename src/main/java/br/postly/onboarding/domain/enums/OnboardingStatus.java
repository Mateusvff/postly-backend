package br.postly.onboarding.domain.enums;

import lombok.Getter;

@Getter
public enum OnboardingStatus {
    PROCESSING,
    COMPLETED,
    FAILED,
    UPDATED
}