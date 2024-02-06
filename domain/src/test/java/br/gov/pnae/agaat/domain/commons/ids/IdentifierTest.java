package br.gov.pnae.agaat.domain.commons.ids;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentifierTest {
    class IdentifierClass extends Identifier<Long> {
        protected IdentifierClass(final Long value) {
            super(value);
        }
    }

    @Test
    void shouldCreateIdentifier() {
        // given
        var id = new IdentifierClass(1L);

        // then
        assertEquals(id, new IdentifierClass(1L));
    }

    @Test
    void shouldCreateIdentifierAndReturnsHashCode() {
        // given
        var primaryId = new IdentifierClass(1L);
        var secondId = new IdentifierClass(1L);

        // then
        assertEquals(primaryId.hashCode(), secondId.hashCode());
    }
}