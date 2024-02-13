package br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.create;

import br.gov.pnae.agaat.application.cecanes.create.CreateCecaneOutput;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCecaneHttpResponse(
        @JsonProperty("cecane_id") Long cecaneId
) {

    public static CreateCecaneHttpResponse mapperTo(final CreateCecaneOutput output) {
        return new CreateCecaneHttpResponse(
                output.id()
        );
    }

}
