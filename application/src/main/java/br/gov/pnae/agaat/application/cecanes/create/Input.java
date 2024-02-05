package br.gov.pnae.agaat.application.cecanes.create;

import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneFactory;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneNome;

public record Input(String nome) {

    public Cecane toAggregate() {

        final var nome = new CecaneNome(this.nome);

        return CecaneFactory.create(nome);
    }

}
