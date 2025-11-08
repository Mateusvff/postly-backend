package br.postly.enrichment.domain.exceptions;

import br.postly.shared.exception.EntityNotFoundException;

public class MetaPageTokenNotFound extends EntityNotFoundException {

    public MetaPageTokenNotFound(String message) {
        super(message);
    }
}
