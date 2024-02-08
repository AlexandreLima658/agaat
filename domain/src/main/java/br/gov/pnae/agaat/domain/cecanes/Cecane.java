package br.gov.pnae.agaat.domain.cecanes;

import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneNome;
import br.gov.pnae.agaat.domain.commons.entities.AggregateRoot;
import br.gov.pnae.agaat.domain.commons.validation.ValidationHandler;

public class Cecane extends AggregateRoot<CecaneId> {
    public CecaneNome nome;

    Cecane(final CecaneId cecaneId, final CecaneNome nome) {
        super(cecaneId);
        this.nome = nome;
    }

    @Override
    public void validate(ValidationHandler handler) {
        new CecaneValidator(Cecane.this, handler).validate();
    }

    public void update(CecaneNome nome) {
        this.nome = nome;
    }

    public String nome() {
        return nome.value();
    }
}
