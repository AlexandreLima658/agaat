package br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.retrieve.by.filter;

import br.gov.pnae.agaat.application.cecanes.retrieve.by.filter.RetrieveCecanesByFilterOutput;
import com.fasterxml.jackson.annotation.JsonProperty;

public record RetrieveCecaneByFilterHttpResponse(
        @JsonProperty("cecane_id") Long cecaneId,
        @JsonProperty("nome") String nome
) {

    public static RetrieveCecaneByFilterHttpResponse mapperTo(final RetrieveCecanesByFilterOutput output) {
        return new RetrieveCecaneByFilterHttpResponse(
                output.id(),
                output.nome()
        );
    }

}
