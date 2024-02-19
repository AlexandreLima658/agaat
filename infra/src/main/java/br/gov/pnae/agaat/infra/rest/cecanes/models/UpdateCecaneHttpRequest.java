package br.gov.pnae.agaat.infra.rest.cecanes.models;

import br.gov.pnae.agaat.application.cecanes.command.update.UpdateCecaneInput;

import java.util.UUID;

public record UpdateCecaneHttpRequest(String nome) {

    public UpdateCecaneInput toInput(final UUID id) {
        return new UpdateCecaneInput(id, nome);
    }

}
