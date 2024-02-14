package br.gov.pnae.agaat.domain.cecanes;

import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.pagination.Pagination;
import br.gov.pnae.agaat.domain.pagination.SearchQuery;

import java.util.Optional;

public interface CecaneRepository {

    Optional<Cecane> findById(final CecaneId id);

    void persist(final Cecane cecane);

    void update(final Cecane cecane);

    void deleteById(final CecaneId id);

    Pagination<Cecane> findAll(SearchQuery aQuery);

    void deleteAll();
}
