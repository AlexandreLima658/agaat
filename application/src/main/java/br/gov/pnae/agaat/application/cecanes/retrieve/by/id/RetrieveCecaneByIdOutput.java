package br.gov.pnae.agaat.application.cecanes.retrieve.by.id;

import br.gov.pnae.agaat.domain.cecanes.Cecane;

public record RetrieveCecaneByIdOutput(Long id, String nome) {
    public static RetrieveCecaneByIdOutput fromAggregate(final Cecane cecane) {
        return new RetrieveCecaneByIdOutput(
                cecane.id().value(),
                cecane.nome()
        );
    }
}
