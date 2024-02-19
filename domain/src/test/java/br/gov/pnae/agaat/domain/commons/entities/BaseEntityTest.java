package br.gov.pnae.agaat.domain.commons.entities;

import br.gov.pnae.agaat.domain.UnitTest;
import br.gov.pnae.agaat.domain.commons.ids.Identifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BaseEntityTest extends UnitTest {
    @Test
    @DisplayName("Deve criar uma entidade base")
    void shouldCreateBaseEntity() {
        // given
        final var expectedIdValue = 1L;

        // when
        final var baseEntity = new BaseEntity<Identifier<Long>>(new Identifier<Long>(expectedIdValue) {}) {};

        // then
        assertNotNull(baseEntity);
        assertEquals(baseEntity, baseEntity);
        assertEquals(expectedIdValue, baseEntity.id().value());
    }

    @Test
    @DisplayName("Deve criar uma entidade base e comparar com um objeto genérico nulo")
    void shouldCreateBaseEntityThenCompareWithNullableGenericObject() {
        // given
        final var expectedIdValue = 1L;

        // when
        final var baseEntity = new BaseEntity<Identifier<Long>>(new Identifier<Long>(expectedIdValue) {}) {};

        // then
        assertNotNull(baseEntity);
        assertEquals(expectedIdValue, baseEntity.id().value());
        assertFalse(baseEntity.equals(null));
        assertFalse(baseEntity.equals(new Object()));
        assertNotEquals(baseEntity, new Object());
    }

    @Test
    @DisplayName("Deve criar uma entidade base e comparar com ela mesma")
    void shouldCreateBaseEntityAndCompareHashCode() {
        // when
        final var firstBaseEntity = new BaseEntity<Identifier<Long>>(new Identifier<Long>(1L) {}) {};
        final var secondBaseEntity = new BaseEntity<Identifier<Long>>(new Identifier<Long>(1L) {}) {};

        // then
        assertNotNull(firstBaseEntity);
        assertNotNull(secondBaseEntity);
        assertEquals(firstBaseEntity.hashCode(), secondBaseEntity.hashCode());
    }

    @Test
    @DisplayName("Deve criar uma entidade base e comparar com outra entidade base")
    void shouldCreateBaseEntityAndCompareIdentifiers() {
        class BaseEntityClass extends BaseEntity<Identifier<Long>> {
            protected BaseEntityClass(Identifier<Long> value) {
                super(value);
            }
        }
        // when
        final var firstBaseEntity = new BaseEntityClass(new Identifier<Long>(1L) {});
        final var secondBaseEntity = new BaseEntityClass(new Identifier<Long>(2L) {});

        // then
        assertNotNull(firstBaseEntity);
        assertNotNull(secondBaseEntity);
        assertFalse(firstBaseEntity.equals(secondBaseEntity));
    }
}