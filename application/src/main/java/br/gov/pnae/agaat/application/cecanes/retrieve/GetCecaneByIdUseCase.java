package br.gov.pnae.agaat.application.cecanes.retrieve;

import br.gov.pnae.agaat.application.UseCase;
import br.gov.pnae.agaat.application.cecanes.create.Output;
import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import jakarta.inject.Named;

import java.util.Objects;
import java.util.Optional;

@Named
public class GetCecaneByIdUseCase extends UseCase<Long, GetCecaneByIdOutput> {
    private final CecaneRepository repository;

    public GetCecaneByIdUseCase(final CecaneRepository repository) {
        this.repository = repository;
    }

    @Override
    public GetCecaneByIdOutput execute(final Long id) {
        final Optional<Cecane> cecane = this.repository.findById(id);

        return GetCecaneByIdOutput.fromAggregate(cecane.get());
    }
}
