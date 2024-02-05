package br.gov.pnae.agaat.domain.cecanes;

import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneNome;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@Tag("unitTest")
class CecaneTest {
    @Test
    void shouldCreateCecane() {
        // given
        final var cecaneId = CecaneId.generate();
        final var nome = "Cecane Nome";

        // when
        final var cecane = new Cecane(cecaneId, new CecaneNome(nome));

        // then
        assertNotNull(cecane);
        assertEquals(cecaneId, cecane.id());
        assertEquals(nome, cecane.nome());
    }
}