package br.gov.pnae.agaat.domain.cecanes.fields;

import br.gov.pnae.agaat.domain.commons.exceptions.DomainException;
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
        assertNotNull(cecaneNome);
        assertEquals(nome, cecaneNome.value());
    }

    @Test
    void shouldNotCreateCecaneNomeWithNullValue() {
        // given
        final String nome = null;
        final var exceptionMessage = "Nome do Cecane não pode ser nulo ou vazio";

        // when
        final var exception = assertThrows(DomainException.class, () -> new CecaneNome(nome));

        // then
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void shouldNotCreateCecaneNomeWithBlankValue() {
        // given
        final var nome = "";
        final var exceptionMessage = "Nome do Cecane não pode ser nulo ou vazio";

        // when
        final var exception = assertThrows(DomainException.class, () -> new CecaneNome(nome));

        // then
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void shouldNotCreateCecaneNomeWithValueMoreThan255Characters() {
        // given
        final var nome = "Cecane Nome".repeat(51);
        final var exceptionMessage = "Nome do Cecane não pode ter mais de 255 caracteres";

        // when
        final var exception = assertThrows(DomainException.class, () -> new CecaneNome(nome));

        // then
        assertEquals(exceptionMessage, exception.getMessage());
    }

}