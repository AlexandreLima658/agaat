package br.gov.pnae.agaat.application.cecanes.retrieve;

import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;

public record GetCecaneByIdOutput(CecaneId id, String nome) {

    public static GetCecaneByIdOutput fromAggregate(final Cecane cecane) {
        return new GetCecaneByIdOutput(
                cecane.id(),
                cecane.nome()
        );
    }
}
