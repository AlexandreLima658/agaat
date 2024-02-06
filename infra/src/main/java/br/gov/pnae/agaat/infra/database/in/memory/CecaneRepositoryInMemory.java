package br.gov.pnae.agaat.infra.database.in.memory;

import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.commons.exceptions.DomainException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CecaneRepositoryInMemory implements CecaneRepository {

    private final Map<Long, Cecane> cecanes;

    public CecaneRepositoryInMemory() {
        this.cecanes = new HashMap<>();
    }

    @Override
    public Optional<Cecane> findById(final Long id) {
        return Optional.ofNullable(cecanes.get(id));
    }

    @Override
    public Optional<Cecane> findByNome(final String nome) {
        return cecanes.values().stream()
                .filter(cecane -> cecane.nome().equals(nome))
                .findFirst();
    }

    @Override
    public Cecane persist(Cecane cecane) {
        cecanes.put(cecane.id().value(), cecane);
        return cecane;
    }

    @Override
    public void update(final Cecane cecane) {
        final var cecaneId = cecane.id().value();

        final var persistedCecane = this.findById(cecaneId)
                .orElseThrow(() -> new DomainException("Cecane not found"));

        cecanes.put(cecaneId, cecane);
    }

    @Override
    public void deleteById(final Long id) {
        final var persistedCecane = this.findById(id)
                .orElseThrow(() -> new DomainException("Cecane not found"));

        cecanes.remove(id);
    }
}
