package br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.update;

import br.gov.pnae.agaat.application.cecanes.update.UpdateCecaneOutput;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.core.util.Json;

public record UpdateCecaneHttpResponse(
        @JsonProperty("cecane_id") Long cecaneId,
        @JsonProperty("nome") String nome
) {

    public static UpdateCecaneHttpResponse mapperTo(final UpdateCecaneOutput output) {
        return new UpdateCecaneHttpResponse(
                output.id(),
                output.nome()
        );
    }

}
