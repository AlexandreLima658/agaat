package br.gov.pnae.agaat.domain.cecanes;

import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("unitTest")
class CecaneFactoryTest {

    @Test
    @DisplayName("Deve criar um Cecane")
    void shouldCreateCecane() {

        // given
        final var nome = "IFCE - Campus Fortaleza";
        final var id = CecaneId.generate();

        // when
        final var cecane = CecaneFactory.create(id, nome);

        // then
        assertNotNull(cecane);
        assertEquals(id, cecane.id());
        assertEquals(nome, cecane.nome());
    }

    @Test
    @DisplayName("Deve criar um Cecane com id gerado")
    void shouldCreateCecaneWithGeneratedId() {

        // given
        final var nome = "IFCE - Campus Fortaleza";

        // when
        final var cecane = CecaneFactory.create(nome);

        // then
        assertNotNull(cecane);
        assertNotNull(cecane.id());
        assertEquals(nome, cecane.nome());
    }

}