package br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.retrieve.by.filter;

import br.gov.pnae.agaat.application.cecanes.retrieve.by.filter.RetrieveCecanesByFilterOutput;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record RetrieveCecaneByFilterHttpResponse(
        @JsonProperty("cecane_id") UUID cecaneId,
        @JsonProperty("nome") String nome
) {

    public static RetrieveCecaneByFilterHttpResponse from(final RetrieveCecanesByFilterOutput output) {
        return new RetrieveCecaneByFilterHttpResponse(
                output.id(),
                output.nome()
        );
    }

}
