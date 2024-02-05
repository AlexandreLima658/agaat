package br.gov.pnae.agaat.domain.cecanes;

import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneNome;

public interface CecaneFactory {

    static Cecane create(final CecaneNome nome) {

        final var id = CecaneId.generate();

        return new Cecane(id, nome);
    }

}
