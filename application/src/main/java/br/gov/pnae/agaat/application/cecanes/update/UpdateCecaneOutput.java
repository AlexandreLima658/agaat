package br.gov.pnae.agaat.application.cecanes.update;

import br.gov.pnae.agaat.domain.cecanes.Cecane;

import java.util.UUID;

public record UpdateCecaneOutput(UUID id, String nome) {
    public static UpdateCecaneOutput fromAggregate(final Cecane cecane) {
        return new UpdateCecaneOutput(
                cecane.id().value(),
                cecane.nome()
        );
    }
}
