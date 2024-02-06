package br.gov.pnae.agaat.domain.cecanes;

import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneNome;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@Tag("unitTest")
class CecaneFactoryTest {
    @Test
    void shouldCreateCecane(){
        // given
        final var nome = "Cecane Nome";

        // when
        final var cecane = CecaneFactory.create(new CecaneNome(nome));

        // then
        assertNotNull(cecane);
        assertNotNull(cecane.id());
        assertEquals(nome, cecane.nome());
    }
}