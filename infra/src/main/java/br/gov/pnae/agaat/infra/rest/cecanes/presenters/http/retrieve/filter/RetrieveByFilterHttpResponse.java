package br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.retrieve.filter;

import br.gov.pnae.agaat.application.cecanes.query.filter.RetrieveCecanesByFilterOutput;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public record RetrieveByFilterHttpResponse(
        @JsonProperty("currentPage") int currentPage,
        @JsonProperty("perPage") int perPage,
        @JsonProperty("total") int total,
        @JsonProperty("items") List<RetrieveCecanesByFilterOutput> items
)
{
    public static RetrieveByFilterHttpResponse mapperTo(final int currentPage, final int perPage, final int total, final List<RetrieveCecanesByFilterOutput> items) {
        return new RetrieveByFilterHttpResponse(
                currentPage,
                perPage,
                total,
                items
        );
    }
}
