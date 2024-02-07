package br.gov.pnae.agaat.application.cecanes.create;

import br.gov.pnae.agaat.application.UseCase;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import jakarta.inject.Named;

import java.util.Objects;

@Named
public class CreateCecaneUseCase extends UseCase<Input, Output> {
    private final CecaneRepository repository;

    public CreateCecaneUseCase(final CecaneRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public Output execute(final Input input) {
        final var cecane = input.toAggregate();

        repository.persist(cecane);

        return Output.fromAggregate(cecane);
    }
}
