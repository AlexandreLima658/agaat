package br.gov.pnae.agaat.domain.cecanes.atributos;

import br.gov.pnae.agaat.domain.commons.exceptions.DomainException;
import br.gov.pnae.agaat.domain.commons.validation.Either;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unitTest")
class CecaneNomeTest {
    @Test
    void shouldCreateCecaneNome() {
        // given
        final var nome = "Cecane Nome";

        // when
        final var cecaneNome = new CecaneNome(nome);

        // then
        assertDoesNotThrow(cecaneNome::validate);
    }

    @Test
    void shouldNotCreateCecaneNomeWithNullValue() {
        // given
        final String nome = null;
        // when
        final CecaneNome cecaneNome = new CecaneNome(nome);

        // then
        assertThrows(DomainException.class, cecaneNome::validate);
        assertNotNull(cecaneNome);
    }

    @Test
    void shouldNotCreateCecaneNomeWithBlankValue() {
        // given
       final String blankName = "  ";

       // when
        final CecaneNome cecaneNome = new CecaneNome(blankName);

        // then
        assertThrows(DomainException.class, cecaneNome::validate);
        assertNotNull(cecaneNome);
    }

    @Test
    void shouldNotCreateCecaneNomeWithValueMoreThan255Characters() {
        // given
        final var nome = "C".repeat(CecaneNome.CECANE_NOME_MAX_LENGTH + 1);
        // when

        final CecaneNome cecaneNome = new CecaneNome(nome);

        // then
        assertThrows(DomainException.class, cecaneNome::validate);
    }
}