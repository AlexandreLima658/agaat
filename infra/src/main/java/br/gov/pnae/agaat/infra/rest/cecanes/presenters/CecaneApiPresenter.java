package br.gov.pnae.agaat.infra.rest.cecanes.presenters;

import br.gov.pnae.agaat.application.cecanes.retrieve.GetCecaneByIdOutput;
import br.gov.pnae.agaat.infra.rest.cecanes.models.CecaneApiOutput;

public interface CecaneApiPresenter {
    static CecaneApiOutput present(final GetCecaneByIdOutput output) {
        return new CecaneApiOutput(
                output.id().value(),
                output.nome()
        );
    }
}
