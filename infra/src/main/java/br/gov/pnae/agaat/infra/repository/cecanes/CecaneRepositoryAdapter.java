package br.gov.pnae.agaat.infra.repository.cecanes;

import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.infra.jpa.cecanes.CecaneJpaMapper;
import br.gov.pnae.agaat.infra.jpa.cecanes.CecaneJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CecaneRepositoryAdapter implements CecaneRepository {

    private final CecaneJpaRepository repository;

    public CecaneRepositoryAdapter(final CecaneJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Cecane> findById(final CecaneId id) {
        return repository
                .findById(id.value())
                .map(CecaneJpaMapper::toAggregate);
    }

    @Override
    public void persist(final Cecane cecane) {
        repository.save(CecaneJpaMapper.toJpaEntity(cecane));
    }

    @Override
    public void update(final Cecane cecane) {
        repository.save(CecaneJpaMapper.toJpaEntity(cecane));
    }

    @Override
    public void deleteById(final CecaneId id) {
        repository.deleteById(id.value());
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

}
