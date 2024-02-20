package br.gov.pnae.agaat.application.cecanes.query.filter;

import br.gov.pnae.agaat.domain.cecanes.Cecane;

import java.util.UUID;

public record RetrieveCecanesByFilterOutput(
        UUID id,
        String nome
) {
    public static RetrieveCecanesByFilterOutput fromAggregate(final Cecane cecane) {
        return new RetrieveCecanesByFilterOutput(
                cecane.id().value(),
                cecane.nome()
        );
    }
}
