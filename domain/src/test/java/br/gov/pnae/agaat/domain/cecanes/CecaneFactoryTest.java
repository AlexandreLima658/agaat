package br.gov.pnae.agaat.domain.cecanes;

import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
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
        final var id = CecaneId.generate();
        // when
        final var cecaneFactory = CecaneFactory.create(new CecaneNome(nome));
        final var cecane = CecaneFactory.create(id, new CecaneNome(nome));

        // then
        assertNotNull(cecaneFactory);
        assertNotNull(cecaneFactory.id());
        assertEquals(nome, cecaneFactory.nome());

        assertNotNull(cecane);
        assertEquals(id, cecane.id());
        assertEquals(nome, cecane.nome());
    }

}