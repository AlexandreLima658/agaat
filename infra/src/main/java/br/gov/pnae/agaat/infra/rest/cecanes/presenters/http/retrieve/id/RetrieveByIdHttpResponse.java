package br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.retrieve.id;

import br.gov.pnae.agaat.application.cecanes.query.id.RetrieveCecaneByIdOutput;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record RetrieveByIdHttpResponse(
        @JsonProperty("id") UUID cecaneId,
        @JsonProperty("nome") String nome

) {

        public static RetrieveByIdHttpResponse from(final RetrieveCecaneByIdOutput output) {
            return new RetrieveByIdHttpResponse(
                    output.id(),
                    output.nome()
            );
        }

}
