package br.gov.pnae.agaat.application.cecanes.query.filter;

import java.util.UUID;

public record RetrieveCecanesByFilterOutput(
        UUID id,
        String nome
) {
}
