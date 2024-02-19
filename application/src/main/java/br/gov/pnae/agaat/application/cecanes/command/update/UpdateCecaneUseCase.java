package br.gov.pnae.agaat.application.cecanes.command.update;

import br.gov.pnae.agaat.application.UseCase;
import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneNome;
import br.gov.pnae.agaat.domain.commons.exceptions.NotFoundException;
import jakarta.inject.Named;

import java.util.Objects;

@Named
public class UpdateCecaneUseCase extends UseCase<UpdateCecaneInput, UpdateCecaneOutput> {
    private final CecaneRepository repository;

    public UpdateCecaneUseCase(CecaneRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    protected UpdateCecaneOutput execute(final UpdateCecaneInput input) {

        final var id = CecaneId.from(input.id());
        final var name = new CecaneNome(input.name());

        final var cecane = this.repository.findById(id).orElseThrow(() -> NotFoundException.with(Cecane.class, id));

        cecane.update(name);

        this.repository.update(cecane);

        return UpdateCecaneOutput.fromAggregate(cecane);

    }
}
