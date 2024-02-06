package br.gov.pnae.agaat.application.cecanes.retrieve;

import br.gov.pnae.agaat.domain.cecanes.Cecane;

public record GetCecaneByIdOutput(Long id, String nome) {

    public static GetCecaneByIdOutput fromAggregate(final Cecane cecane) {
        return new GetCecaneByIdOutput(
                cecane.id().value(),
                cecane.nome()
        );
    }
}
