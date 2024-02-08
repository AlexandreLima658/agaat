package br.gov.pnae.agaat.infra.rest.cecanes.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CecaneListResponse(
        @JsonProperty("id") Long id,
        @JsonProperty("nome") String nome
) {
}
