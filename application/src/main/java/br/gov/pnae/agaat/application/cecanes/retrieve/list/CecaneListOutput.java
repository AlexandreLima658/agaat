package br.gov.pnae.agaat.application.cecanes.retrieve.list;

import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;

public record CecaneListOutput(
        CecaneId id,
        String nome
) {
    public static CecaneListOutput fromAggregate(final Cecane cecane) {
        return new CecaneListOutput(
                cecane.id(),
                cecane.nome()
        );
    }
}
