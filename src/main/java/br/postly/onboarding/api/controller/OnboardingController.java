package br.postly.onboarding.api.controller;

import br.postly.onboarding.api.dto.OnboardingRequest;
import br.postly.onboarding.domain.service.OnboardingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/onboarding")
@RequiredArgsConstructor
public class OnboardingController {

    private final OnboardingService onboardingService;

    public ResponseEntity<Void> onboardCreator(@Valid OnboardingRequest onboardingRequest) {
        onboardingService.onboardCreator(onboardingRequest);
        return ResponseEntity.noContent().build();
    }
}
