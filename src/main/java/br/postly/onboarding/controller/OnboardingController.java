package br.postly.onboarding.controller;

import br.postly.onboarding.dto.OnboardingRequest;
import br.postly.onboarding.service.OnboardingService;
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

    @ApiOperation(summary = "Onboard a new creator")
    public ResponseEntity<Void> onboardCreator(@Valid OnboardingRequest onboardingRequest) {
        onboardingService.onboardCreator(onboardingRequest);
        return ResponseEntity.noContent().build();
    }
}
