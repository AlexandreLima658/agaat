package br.gov.pnae.agaat.application.cecanes.delete;

import br.gov.pnae.agaat.application.UnitUseCase;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import jakarta.inject.Named;

import java.util.Objects;

@Named
public class DeleteCecaneUseCase extends UnitUseCase<Long> {
    private final CecaneRepository repository;

    public DeleteCecaneUseCase(final CecaneRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public void execute(final Long id) {
        this.repository.deleteById(CecaneId.from(id));
    }
}
