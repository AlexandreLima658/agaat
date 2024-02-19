package br.gov.pnae.agaat.application.cecanes.command.delete;

import br.gov.pnae.agaat.application.UnitUseCase;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import jakarta.inject.Named;

import java.util.Objects;
import java.util.UUID;

@Named
public class DeleteCecaneUseCase extends UnitUseCase<UUID> {
    private final CecaneRepository repository;

    public DeleteCecaneUseCase(final CecaneRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public void execute(final UUID id) {
        this.repository.deleteById(CecaneId.from(id));
    }
}
