package br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.create;

import br.gov.pnae.agaat.application.cecanes.command.create.CreateCecaneOutput;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record CreateCecaneHttpResponse(
        @JsonProperty("cecane_id") UUID cecaneId
) {

    public static CreateCecaneHttpResponse from(final CreateCecaneOutput output) {
        return new CreateCecaneHttpResponse(
                output.id()
        );
    }

}
