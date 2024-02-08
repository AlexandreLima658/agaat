package br.gov.pnae.agaat.infra.rest.cecanes.presenters;

import br.gov.pnae.agaat.application.cecanes.retrieve.get.GetCecaneByIdOutput;
import br.gov.pnae.agaat.application.cecanes.retrieve.list.CecaneListOutput;
import br.gov.pnae.agaat.infra.rest.cecanes.models.CecaneApiOutput;
import br.gov.pnae.agaat.infra.rest.cecanes.models.CecaneListResponse;

public interface CecaneApiPresenter {
    static CecaneApiOutput present(final GetCecaneByIdOutput output) {
        return new CecaneApiOutput(
                output.id().value(),
                output.nome()
        );
    }

    static CecaneListResponse present(final CecaneListOutput output) {
        return new CecaneListResponse(
                output.id().value(),
                output.nome()
        );
    }
}
