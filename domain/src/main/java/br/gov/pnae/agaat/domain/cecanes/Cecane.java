package br.gov.pnae.agaat.domain.cecanes;

import br.gov.pnae.agaat.domain.cecanes.fields.CecaneId;
import br.gov.pnae.agaat.domain.cecanes.fields.CecaneNome;
import br.gov.pnae.agaat.domain.commons.entities.AggregateRoot;

public class Cecane extends AggregateRoot<CecaneId> {

    public CecaneNome nome;

    Cecane(CecaneId cecaneId, CecaneNome name) {
        super(cecaneId);
        this.nome = name;
    }

    public String getName() {
        return nome.value();
    }

}
