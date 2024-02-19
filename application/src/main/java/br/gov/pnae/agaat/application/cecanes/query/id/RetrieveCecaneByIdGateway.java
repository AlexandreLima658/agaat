package br.gov.pnae.agaat.application.cecanes.query.id;

import java.util.UUID;

public interface RetrieveCecaneByIdGateway {
    RetrieveCecaneByIdOutput execute(final UUID id);

}
