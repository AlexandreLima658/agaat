package br.gov.pnae.agaat.domain.cecanes;

import br.gov.pnae.agaat.domain.cecanes.fields.CecaneId;
import br.gov.pnae.agaat.domain.cecanes.fields.CecaneNome;

public interface CecaneFactory {

    static Cecane create(
            final CecaneNome nome
    ) {

        return new Cecane(
                CecaneId.generate(),
                nome
        );
    }

}
