package br.gov.pnae.agaat.domain.commons.exceptions;

import br.gov.pnae.agaat.domain.commons.entities.AggregateRoot;
import br.gov.pnae.agaat.domain.commons.ids.Identifier;
import br.gov.pnae.agaat.domain.commons.validation.Error;

import java.util.Collections;
import java.util.List;

public class NotFoundException extends DomainException {
    public NotFoundException(final String message, final List<Error> errors) {
        super(message, errors);
    }

    public static NotFoundException with(final Class<? extends AggregateRoot<?>> anAggregate, final Identifier id) {
        final var error = "%s com ID %d não encontrado"
                .formatted(anAggregate.getSimpleName(), id.value());
        return new NotFoundException(error, Collections.emptyList());
    }
}
