package br.gov.pnae.agaat.domain.cecanes.atributos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("unitTest")
class CecaneIdTest {
    @Test
    @DisplayName("Deve criar um CecaneId a partir de um valor")
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
    @DisplayName("Deve gerar um CecaneId")
    void shouldGenerateCecaneId() {
        // given

        // when
        final var cecaneId = CecaneId.generate();

        // then
        assertNotNull(cecaneId);
    }

}