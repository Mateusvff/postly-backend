package br.postly.enrichment.domain.service;

import br.postly.enrichment.api.dto.MetaPageResponse;
import br.postly.enrichment.domain.model.MetaPageToken;
import br.postly.enrichment.domain.repository.MetaPageTokenRepository;
import br.postly.enrichment.infrastructure.MetaGraphApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetaAuthService {

    @Value("${meta.long.lived.token}")
    private String longLivedToken;

    private final MetaGraphApiClient metaGraphClient;
    private final MetaPageTokenRepository metaPageTokenRepository;

    public void saveMetaPageAccessToken() {
        MetaPageResponse metaPageResponse = metaGraphClient.listPages(longLivedToken);

        MetaPageToken metaToken = new MetaPageToken();
        metaToken.setPageAccessToken(metaPageResponse.data().getFirst().accessToken());
        metaToken.setIgUserId(metaPageResponse.data().getFirst().id());

        metaPageTokenRepository.save(metaToken);
    }
}
