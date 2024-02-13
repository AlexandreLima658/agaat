package br.gov.pnae.agaat.application.cecanes.retrieve.by.filter;

import br.gov.pnae.agaat.domain.cecanes.Cecane;

public record RetrieveCecanesByFilterOutput(
        Long id,
        String nome
) {
    public static RetrieveCecanesByFilterOutput fromAggregate(final Cecane cecane) {
        return new RetrieveCecanesByFilterOutput(
                cecane.id().value(),
                cecane.nome()
        );
    }
}
