package br.gov.pnae.agaat.domain.cecanes.fields;

import br.gov.pnae.agaat.domain.commons.ids.Identifier;

import java.util.UUID;

public final class CecaneId extends Identifier<Long> {
    private CecaneId(final Long value) {
        super(value);
    }

    public static CecaneId from(final Long value) {
        return new CecaneId(value);
    }

    public static CecaneId generate() {
        return new CecaneId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
    }

}
