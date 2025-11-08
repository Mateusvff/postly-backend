package br.postly.enrichment.domain.exceptions;

import br.postly.shared.exception.EntityNotFoundException;

public class MetaPageTokenNotFoundException extends EntityNotFoundException {

    public MetaPageTokenNotFoundException(String message) {
        super(message);
    }
}
