package br.gov.pnae.agaat.application.cecanes.create;

import br.gov.pnae.agaat.domain.cecanes.Cecane;

public record CreateCecaneOutput(Long id) {
    public static CreateCecaneOutput fromAggregate(final Cecane cecane) {
        final var id = cecane.id().value();

        return new CreateCecaneOutput(
                id
        );
    }
}
