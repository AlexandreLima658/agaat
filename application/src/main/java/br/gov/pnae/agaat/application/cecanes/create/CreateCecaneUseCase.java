package br.gov.pnae.agaat.application.cecanes.create;

import br.gov.pnae.agaat.application.UseCase;
import br.gov.pnae.agaat.domain.cecanes.CecaneFactory;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import jakarta.inject.Named;

import java.util.Objects;

@Named
public class CreateCecaneUseCase extends UseCase<CreateCecaneInput, CreateCecaneOutput> {
    private final CecaneRepository repository;

    public CreateCecaneUseCase(final CecaneRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public CreateCecaneOutput execute(final CreateCecaneInput input) {

        final var cecane = CecaneFactory.create(
                input.nome()
        );

        this.repository.persist(cecane);

        return CreateCecaneOutput.fromAggregate(cecane);

    }

}
