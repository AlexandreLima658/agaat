package br.gov.pnae.agaat.application.cecanes.create;

import br.gov.pnae.agaat.domain.cecanes.Cecane;

public record Output(Long id) {

    public static Output fromAggregate(final Cecane cecane) {
        final var id = cecane.id().value();

        return new Output(
                id
        );
    }

}
