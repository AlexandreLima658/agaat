package br.gov.pnae.agaat.domain.cecanes;

import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;

import java.util.Optional;

public interface CecaneRepository {

    Optional<Cecane> findById(final CecaneId id);

    Optional<Cecane> findByNome(final String nome);

    Cecane persist(final Cecane cecane);

    void update(final Cecane cecane);

    void deleteById(final CecaneId id);
}
