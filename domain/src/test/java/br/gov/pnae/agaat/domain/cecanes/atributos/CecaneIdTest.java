package br.gov.pnae.agaat.domain.cecanes.atributos;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unitTest")
class CecaneIdTest {
    @Test
    void shouldCreateCecaneIdFromValue() {
        // given
        final var expectedId = 1L;

        // when
        final var cecaneId = CecaneId.from(expectedId);

        // then
        assertNotNull(cecaneId);
        assertEquals(expectedId, cecaneId.value());
    }

    @Test
    void shouldGenerateCecaneId() {
        // given

        // when
        final var cecaneId = CecaneId.generate();

        // then
        assertNotNull(cecaneId);
    }
}