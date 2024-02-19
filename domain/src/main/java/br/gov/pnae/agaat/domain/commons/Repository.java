package br.gov.pnae.agaat.domain.commons;

import br.gov.pnae.agaat.domain.commons.entities.AggregateRoot;
import br.gov.pnae.agaat.domain.commons.ids.Identifier;

import java.util.Optional;

public interface Repository<T extends AggregateRoot<?>, ID extends Identifier<?>> {

    Optional<T> findById(final ID id);

    void persist(final T aggregate);

    void update(final T aggregate);

    void deleteById(final ID id);

    void deleteAll();

}
