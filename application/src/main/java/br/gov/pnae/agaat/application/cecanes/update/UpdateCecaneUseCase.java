package br.gov.pnae.agaat.application.cecanes.update;

import br.gov.pnae.agaat.application.UseCase;
import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneNome;
import br.gov.pnae.agaat.domain.commons.Either;
import br.gov.pnae.agaat.domain.commons.exceptions.DomainException;
import br.gov.pnae.agaat.domain.commons.exceptions.NotFoundException;
import jakarta.inject.Named;

import java.util.Objects;
import java.util.function.Supplier;

@Named
public class UpdateCecaneUseCase extends UseCase<UpdateCecaneCommand, Either<DomainException, UpdateCecaneOutput>> {
    private final CecaneRepository repository;

    public UpdateCecaneUseCase(CecaneRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Either<DomainException, UpdateCecaneOutput> execute(final UpdateCecaneCommand command) {
        try {
            final var id = CecaneId.from(command.id());
            final var name = new CecaneNome(command.name());

            final var cecane = this.repository.findById(id).orElseThrow(() -> NotFoundException.with(Cecane.class, id));
            cecane.update(new CecaneNome(cecane.nome()));

            this.repository.update(cecane);

            return Either.right(UpdateCecaneOutput.fromAggregate(cecane));
        } catch (final NotFoundException ex) {
            return Either.left(ex);
        }
    }
}
