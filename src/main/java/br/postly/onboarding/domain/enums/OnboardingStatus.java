package br.postly.onboarding.domain.enums;

import lombok.Getter;

@Getter
public enum OnboardingStatus {
    PROCESSING_REFERENCES,
    COMPLETED,
    FAILED,
    UPDATED
}