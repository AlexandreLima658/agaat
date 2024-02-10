package br.gov.pnae.agaat.application.cecanes.create;

import br.gov.pnae.agaat.application.UseCase;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.commons.Either;
import br.gov.pnae.agaat.domain.commons.exceptions.DomainException;
import jakarta.inject.Named;

import java.util.Objects;

@Named
public class CreateCecaneUseCase extends UseCase<CreateCecaneInput, Either<DomainException, CreateCecaneOutput>> {
    private final CecaneRepository repository;

    public CreateCecaneUseCase(final CecaneRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Either<DomainException, CreateCecaneOutput> execute(final CreateCecaneInput input) {

        try {

            final var cecane = input.toAggregate();

            this.repository.persist(cecane);

            return Either.right(CreateCecaneOutput.fromAggregate(cecane));

        } catch (final DomainException e) {
            return Either.left(e);
        }

    }
}
