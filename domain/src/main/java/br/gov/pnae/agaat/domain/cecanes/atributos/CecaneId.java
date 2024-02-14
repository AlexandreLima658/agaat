package br.gov.pnae.agaat.domain.cecanes.atributos;

import br.gov.pnae.agaat.domain.commons.ids.Identifier;

import java.util.UUID;

public class CecaneId extends Identifier<UUID> {
    private CecaneId(final UUID value) {
        super(value);
    }

    public static CecaneId from(final UUID value) {
        return new CecaneId(value);
    }

    public static CecaneId generate() {
        return new CecaneId(UUID.randomUUID());
    }
}
