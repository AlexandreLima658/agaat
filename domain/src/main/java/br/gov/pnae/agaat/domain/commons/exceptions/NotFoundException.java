package br.gov.pnae.agaat.domain.commons.exceptions;

import br.gov.pnae.agaat.domain.commons.entities.AggregateRoot;
import br.gov.pnae.agaat.domain.commons.ids.Identifier;

public class NotFoundException extends DomainException {
    public NotFoundException(final String message) {
        super(message);
    }

    public static NotFoundException with(final Class<? extends AggregateRoot<?>> anAggregate, final Identifier id) {
        final var error = "%s com ID %d não encontrado".formatted(anAggregate.getSimpleName(), id.value());
        return new NotFoundException(error);
    }
}
