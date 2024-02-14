package br.gov.pnae.agaat.application.cecanes.create;

import br.gov.pnae.agaat.domain.cecanes.Cecane;

import java.util.UUID;

public record CreateCecaneOutput(UUID id) {
    public static CreateCecaneOutput fromAggregate(final Cecane cecane) {
        final var id = cecane.id().value();

        return new CreateCecaneOutput(
                id
        );
    }
}
