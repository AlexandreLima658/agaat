package br.gov.pnae.agaat.application.cecanes.retrieve.by.id;

import br.gov.pnae.agaat.application.UseCase;
import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.commons.exceptions.NotFoundException;
import jakarta.inject.Named;

import java.util.Objects;
import java.util.UUID;

@Named
public class RetrieveCecaneByIdUseCase extends UseCase<UUID, RetrieveCecaneByIdOutput> {
    private final CecaneRepository repository;

    public RetrieveCecaneByIdUseCase(final CecaneRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public RetrieveCecaneByIdOutput execute(final UUID id) {

        final var cecaneId = CecaneId.from(id);

        return this.repository
                .findById(cecaneId)
                .map(RetrieveCecaneByIdOutput::fromAggregate)
                .orElseThrow(() -> NotFoundException.with(Cecane.class, cecaneId));
    }

}
