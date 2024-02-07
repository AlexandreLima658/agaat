package br.gov.pnae.agaat.application.cecanes.update;

import br.gov.pnae.agaat.domain.cecanes.Cecane;

public record UpdateCecaneOutput(Long id, String name) {
    public static UpdateCecaneOutput fromAggregate(final Cecane cecane) {
        return new UpdateCecaneOutput(
                cecane.id().value(),
                cecane.nome()
        );
    }
}
