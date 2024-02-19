package br.gov.pnae.agaat.application.cecanes.create;

import br.gov.pnae.agaat.application.IntegrationTest;
import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.commons.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class CreateCecaneUseCaseTest extends IntegrationTest {

    @Test
    @DisplayName("Deve criar uma cecane com input válido")
    void shouldCreateCecaneWithValidInput() {
        // given
        final var repository = mock(CecaneRepository.class);
        final var createCecaneUseCase = new CreateCecaneUseCase(repository);

        final var input = new CreateCecaneInput("UFC Russas");

        // when
        final var result = createCecaneUseCase.execute(input);

        // then
        Assertions.assertNotNull(result.id());
        verify(repository, times(1)).persist(any(Cecane.class));
    }

    @DisplayName("Deve retornar erro com input inválido")
    @Test
    void shouldReturnErrorWithInvalidInput() {
        // given
        final var expectedErrorMessage = "Nome do Cecane não pode ser nulo ou vazio";

        final var repository = mock(CecaneRepository.class);
        final var createCecaneUseCase = new CreateCecaneUseCase(repository);

        final var input = new CreateCecaneInput("");

        // when
        final var error = Assertions.assertThrows(DomainException.class, () -> createCecaneUseCase.execute(input));

        // then
        assertEquals(expectedErrorMessage, error.getMessage());
        verify(repository, never()).persist(any(Cecane.class));
    }
}