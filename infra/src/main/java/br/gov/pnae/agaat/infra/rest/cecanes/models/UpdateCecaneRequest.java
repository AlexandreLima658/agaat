package br.gov.pnae.agaat.infra.rest.cecanes.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateCecaneRequest(
        @JsonProperty ("nome") String nome
) {
}
