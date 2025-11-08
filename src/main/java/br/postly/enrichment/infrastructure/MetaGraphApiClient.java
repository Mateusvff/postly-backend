package br.postly.enrichment.infrastructure;

import br.postly.enrichment.api.dto.BusinessDiscoveryResponse;
import br.postly.enrichment.api.dto.MetaPageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "metaGraph", url = "${meta.graph.baseurl}")
public interface MetaGraphApiClient {

    @GetMapping(value = "/me/accounts")
    MetaPageResponse listPages(@RequestParam("access_token") String accessToken);

    @GetMapping("/{igUserId}")
    BusinessDiscoveryResponse getBusinessDiscovery(
            @PathVariable("igUserId") String igUserId,
            @RequestParam("fields") String fields,
            @RequestParam("access_token") String accessToken
    );

}
