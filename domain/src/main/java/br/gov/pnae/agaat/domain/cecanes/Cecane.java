package br.gov.pnae.agaat.domain.cecanes;

import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneNome;
import br.gov.pnae.agaat.domain.commons.entities.AggregateRoot;

public class Cecane extends AggregateRoot<CecaneId> {

    CecaneNome nome;

    Cecane(final CecaneId cecaneId, final CecaneNome nome) {
        super(cecaneId);
        this.nome = nome;
    }

    public void update(final CecaneNome nome) {

        this.nome = nome;

    }

    public String nome() {
        return nome.value();
    }
}
