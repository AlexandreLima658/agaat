package br.gov.pnae.agaat.domain.commons.entities;

import br.gov.pnae.agaat.domain.commons.ids.Identifier;
import br.gov.pnae.agaat.domain.commons.validation.ValidationHandler;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unitTest")
class BaseEntityTest {
    @Test
    void shouldCreateBaseEntity() {
        // given
        final var expectedIdValue = 1L;

        // when
        final var baseEntity = new BaseEntity<Identifier<Long>>(new Identifier<Long>(expectedIdValue) {}){
            @Override
            public void validate(ValidationHandler handler) {}
        };

        // then
        assertNotNull(baseEntity);
        assertEquals(baseEntity, baseEntity);
        assertEquals(expectedIdValue, baseEntity.id().value());
    }

    @Test
    void shouldCreateBaseEntityThenCompareWithNullableGenericObject() {
        // given
        final var expectedIdValue = 1L;

        // when
        final var baseEntity = new BaseEntity<Identifier<Long>>(new Identifier<Long>(expectedIdValue) {}){
            @Override
            public void validate(ValidationHandler handler) {}
        };

        // then
        assertNotNull(baseEntity);
        assertEquals(expectedIdValue, baseEntity.id().value());
        assertFalse(baseEntity.equals(null));
        assertFalse(baseEntity.equals(new Object()));
    }

    @Test
    void shouldCreateBaseEntityAndCompareHashCode() {
        // when
        final var firstBaseEntity = new BaseEntity<Identifier<Long>>(new Identifier<Long>(1L) {}){
            @Override
            public void validate(ValidationHandler handler) {}
        };
        final var secondBaseEntity = new BaseEntity<Identifier<Long>>(new Identifier<Long>(1L) {}){
            @Override
            public void validate(ValidationHandler handler) {}
        };

        // then
        assertNotNull(firstBaseEntity);
        assertNotNull(secondBaseEntity);
        assertEquals(firstBaseEntity.hashCode(), secondBaseEntity.hashCode());
    }
}