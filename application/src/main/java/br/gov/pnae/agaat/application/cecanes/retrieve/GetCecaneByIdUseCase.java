package br.gov.pnae.agaat.application.cecanes.retrieve;

import br.gov.pnae.agaat.application.UseCase;
import br.gov.pnae.agaat.application.cecanes.create.Output;
import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.commons.exceptions.DomainException;
import br.gov.pnae.agaat.domain.commons.exceptions.NotFoundException;
import jakarta.inject.Named;

import java.util.function.Supplier;

@Named
public class GetCecaneByIdUseCase extends UseCase<Long, GetCecaneByIdOutput> {
    private final CecaneRepository repository;

    public GetCecaneByIdUseCase(final CecaneRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetCecaneByIdOutput execute(final Long id) {
        return this.repository.findById(id)
                .map(GetCecaneByIdOutput::fromAggregate)
                .orElseThrow(notFound(CecaneId.from(id)));
    }

    private static Supplier<DomainException> notFound(final CecaneId id) {
        return () -> NotFoundException.with(Cecane.class, id);
    }
}
