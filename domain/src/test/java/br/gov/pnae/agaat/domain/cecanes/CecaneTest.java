package br.gov.pnae.agaat.domain.cecanes;

import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneNome;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("unitTest")
class CecaneTest {

    @Test
    void shouldUpdateCecane() {

        // given
        final var nome = "IFCE - Campus Acaraú";
        final var cecane = CecaneFactory.create(nome);

        // when
        final var novoNome = "IFCE - Campus Fortaleza";
        cecane.update(new CecaneNome(novoNome));

        // then
        assertEquals(novoNome, cecane.nome());
    }
}