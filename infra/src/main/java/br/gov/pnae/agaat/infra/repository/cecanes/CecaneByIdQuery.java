package br.gov.pnae.agaat.infra.repository.cecanes;

import br.gov.pnae.agaat.application.cecanes.query.id.RetrieveCecaneByIdGateway;
import br.gov.pnae.agaat.application.cecanes.query.id.RetrieveCecaneByIdOutput;
import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.commons.exceptions.NotFoundException;
import br.gov.pnae.agaat.infra.jpa.cecanes.CecaneJpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CecaneByIdQuery implements RetrieveCecaneByIdGateway {

    private final CecaneJpaRepository repository;

    public CecaneByIdQuery(final CecaneJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public RetrieveCecaneByIdOutput execute(UUID id) {
        return repository
                .findById(id)
                .map(jpa -> new RetrieveCecaneByIdOutput(jpa.getId(), jpa.getNome()))
                .orElseThrow(() -> NotFoundException.with(Cecane.class, CecaneId.from(id)));
    }
}
