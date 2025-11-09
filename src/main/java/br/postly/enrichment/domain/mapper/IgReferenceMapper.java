package br.postly.enrichment.domain.mapper;

import br.postly.enrichment.api.dto.BusinessDiscoveryResponse;
import br.postly.enrichment.domain.model.IgReference;
import org.springframework.stereotype.Component;

@Component
public class IgReferenceMapper {

    public IgReference mapToIgReference(BusinessDiscoveryResponse response) {
        IgReference igReference = new IgReference();
        igReference.setUsername(response.businessDiscovery().name());
        igReference.setBiography(response.businessDiscovery().biography());
        igReference.setProfilePictureUrl(response.businessDiscovery().profilePictureUrl());
        return igReference;
    }

}
