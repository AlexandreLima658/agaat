package br.gov.pnae.agaat.infra.rest.cecanes.models;

import br.gov.pnae.agaat.application.cecanes.update.UpdateCecaneInput;

public record UpdateCecaneHttpRequest(String nome) {

    public UpdateCecaneInput toInput(final Long id) {
        return new UpdateCecaneInput(id, nome);
    }

}
