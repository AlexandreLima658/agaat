package br.gov.pnae.agaat.domain.cecanes.atributos;

import br.gov.pnae.agaat.domain.commons.exceptions.DomainException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("unitTest")
class CecaneNomeTest {
    @Test
    void shouldCreateCecaneNome() {
        // given
        final var nome = "Instituto Federal do Ceará - Campus Fortaleza";

        // when
        final var cecaneNome = new CecaneNome(nome);

        // then
        assertEquals(nome, cecaneNome.value());
    }

    @Test
    void shouldNotCreateCecaneNomeWithNullValue() {

        // given
        final var expectedMessage = "Nome do Cecane não pode ser nulo ou vazio";

        // when
        final var anError = assertThrows(DomainException.class, () -> new CecaneNome(null));

        // then
        assertEquals(expectedMessage, anError.getMessage());
    }

    @Test
    void shouldNotCreateCecaneNomeWithBlankValue() {
        // given
        final var blankName = "  ";
        final var expectedMessage = "Nome do Cecane não pode ser nulo ou vazio";

        // when
        final var anError = assertThrows(DomainException.class, () -> new CecaneNome(blankName));

        // then
        assertEquals(expectedMessage, anError.getMessage());

    }

    @Test
    void shouldNotCreateCecaneNomeWithValueMoreThan255Characters() {
        // given
        final var nome = "C".repeat(CecaneNome.CECANE_NOME_MAX_LENGTH + 1);
        final var expectedMessage = "Nome do Cecane não pode ter mais de 255 caracteres";

        // when

        final var anError = assertThrows(DomainException.class, () -> new CecaneNome(nome));

        // then
        assertEquals(expectedMessage, anError.getMessage());
    }
}