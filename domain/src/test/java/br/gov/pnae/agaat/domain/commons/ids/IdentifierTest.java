package br.gov.pnae.agaat.domain.commons.ids;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentifierTest  {

    @Test
    @DisplayName("Deve criar um Identifier a partir de um valor")
    void shouldCreateIdentifier() {
        // given
        final var expectedIdValue = 1L;

        // when
        final var id = new Identifier<Long>(expectedIdValue) {
        };

        // then
        assertNotNull(id);
        assertEquals(expectedIdValue, id.value());
    }

    @Test
    @DisplayName("Deve criar um Identifier a partir de um valor e comparar com um objeto genérico não nulo")
    void shouldCreateIdentifierThenCompareWithNullableGenericObject() {
        // given
        final var expectedIdValue = 1L;

        // when
        final var id = new Identifier<>(1L) {
        };

        // then
        assertNotNull(id);
        assertEquals(expectedIdValue, id.value());
        assertFalse(id.equals(null));
        assertFalse(id.equals(new Object()));
    }

    @Test
    @DisplayName("Deve criar identificadores e comparar os valores iguais")
    void shouldCreateIdentifiersThenCompareValue() {
        class IdentifierClass extends Identifier<Long> {
            protected IdentifierClass(final Long value) {
                super(value);
            }
        }

        // when
        final var firstId = new IdentifierClass(1L);
        final var secondId = new IdentifierClass(1L);

        // then
        assertNotNull(firstId);
        assertNotNull(secondId);
        assertEquals(firstId, secondId);
    }

    @Test
    @DisplayName("Deve criar Identificadores e comparar os hash codes")
    void shouldCreateIdentifiersAndCompareHashCode() {

        // when
        final var firstId = new Identifier<>(1L) {
        };
        final var secondId = new Identifier<>(2L) {
        };

        // then
        assertNotNull(firstId);
        assertNotNull(secondId);
        assertNotEquals(firstId.hashCode(), secondId.hashCode());
    }

}