package br.gov.pnae.agaat.domain.commons.entities;

import br.gov.pnae.agaat.domain.commons.ids.Identifier;

public abstract class AggregateRoot<Id extends Identifier<?>> extends BaseEntity<Id> {

    protected AggregateRoot(final Id id) {
        super(id);
    }

}
