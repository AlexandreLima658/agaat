package br.gov.pnae.agaat.infra.rest.cecanes.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CecaneApiOutput(
        @JsonProperty("id") Long id,
        @JsonProperty("name") String name) {

}
