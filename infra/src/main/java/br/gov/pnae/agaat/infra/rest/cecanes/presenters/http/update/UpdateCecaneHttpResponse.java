package br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.update;

import br.gov.pnae.agaat.application.cecanes.command.update.UpdateCecaneOutput;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record UpdateCecaneHttpResponse(
        @JsonProperty("cecane_id") UUID cecaneId,
        @JsonProperty("nome") String nome
) {

    public static UpdateCecaneHttpResponse mapperTo(final UpdateCecaneOutput output) {
        return new UpdateCecaneHttpResponse(
                output.id(),
                output.nome()
        );
    }

}
