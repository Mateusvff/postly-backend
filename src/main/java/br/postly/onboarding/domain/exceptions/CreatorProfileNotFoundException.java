package br.postly.onboarding.domain.exceptions;

import br.postly.shared.exception.EntityNotFoundException;

public class CreatorProfileNotFoundException extends EntityNotFoundException {
    public CreatorProfileNotFoundException(String message) {
        super(message);
    }
}
