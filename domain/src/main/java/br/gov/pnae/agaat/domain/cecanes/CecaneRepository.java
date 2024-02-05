package br.gov.pnae.agaat.domain.cecanes;

import java.util.Optional;

public interface CecaneRepository {

    Optional<Cecane> findById(final Long id);

    Optional<Cecane> findByNome(final String nome);

    Cecane persist(final Cecane cecane);

    void update(final Cecane cecane);

}
