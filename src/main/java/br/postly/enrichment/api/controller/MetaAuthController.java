package br.postly.enrichment.api.controller;

import br.postly.enrichment.domain.service.MetaAuthService;
import br.postly.enrichment.domain.service.MetaEnrichmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth/meta")
@RequiredArgsConstructor
public class MetaAuthController {

    private final MetaAuthService metaAuthService;
    private final MetaEnrichmentService metaEnrichmentService;

    @PostMapping("/authenticate")
    public ResponseEntity<Void> authenticate() {
        metaAuthService.saveMetaPageAccessToken();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/enrich")
    public ResponseEntity<Void> enrich() {
        metaEnrichmentService.enrichReferences();
        return ResponseEntity.ok().build();
    }

}
