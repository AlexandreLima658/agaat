package br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.retrieve.by.id;

import br.gov.pnae.agaat.application.cecanes.retrieve.by.id.RetrieveCecaneByIdOutput;
import com.fasterxml.jackson.annotation.JsonProperty;

public record RetrieveCecaneByIdHttpResponse(
        @JsonProperty("cecane_id") Long cecaneId,
        @JsonProperty("nome") String nome
) {


    public static RetrieveCecaneByIdHttpResponse mapperTo(final RetrieveCecaneByIdOutput output) {
        return new RetrieveCecaneByIdHttpResponse(
                output.id(),
                output.nome()
        );
    }

}
