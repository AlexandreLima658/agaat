package br.gov.pnae.agaat.domain.cecanes;

import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneNome;

public interface CecaneFactory {
    static Cecane create(final CecaneId id, final String nome) {
        return new Cecane(
                id,
                new CecaneNome(nome)
        );
    }

    static Cecane create(final String nome) {

        final var cecaneId = CecaneId.generate();

        return new Cecane(
                cecaneId,
                new CecaneNome(nome)
        );
    }

}
