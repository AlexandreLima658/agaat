package br.gov.pnae.agaat.domain.commons.ids;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentifierTest {
    @Test
    void shouldCreateIdentifier() {
        // given
        final var expectedIdValue = 1L;

        // when
        final var id = new Identifier<Long>(expectedIdValue) {};

        // then
        assertNotNull(id);
        assertEquals(expectedIdValue, id.value());
    }

    @Test
    void shouldCreateIdentifierThenCompare() {
        // given
        final var expectedIdValue = 1L;

        // when
        final var id = new Identifier<Long>(expectedIdValue) {};

        // then
        assertNotNull(id);
        assertEquals(expectedIdValue, id.value());
    }

    @Test
    void shouldCreateIdentifierThenCompareWithNullableGenericObject() {
        // given
        final var expectedIdValue = 1L;

        // when
        final var id = new Identifier<Long>(1L) {};

        // then
        assertNotNull(id);
        assertEquals(expectedIdValue, id.value());
        assertFalse(id.equals(null));
        assertFalse(id.equals(new Object()));
    }

    @Test
    void shouldCreateIdentifiersThenCompareValue() {
        class IdentifierClass extends Identifier<Long> {
            protected IdentifierClass(Long value) {
                super(value);
            }
        }

        // when
        final var firstId = new IdentifierClass(1L);
        final var secondId = new IdentifierClass(1L);

        // then
        assertNotNull(firstId);
        assertNotNull(secondId);
        assertTrue(firstId.equals(secondId));
    }

    @Test
    void shouldCreateIdentifiersAndCompareHashCode() {
        // when
        final var firstId = new Identifier<Long>(1L) {};
        final var secondId = new Identifier<Long>(1L) {};

        // then
        assertNotNull(firstId);
        assertNotNull(secondId);
        assertEquals(firstId.hashCode(), secondId.hashCode());
    }
}