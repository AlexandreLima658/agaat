package br.gov.pnae.agaat.application.cecanes.update;

import br.gov.pnae.agaat.domain.cecanes.CecaneFactory;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.commons.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@Tag("integrationTest")
class UpdateCecaneUseCaseTest {

    @Test
    @DisplayName("Deve atualizar e persistir um Cecane")
    void shouldUpdateCecaneUseCase() {

        // given
        final var repository = mock(CecaneRepository.class);
        final var cecane = CecaneFactory.create(
                "IFCE - Campus Acaraú"
        );

        final var novoNome = "IFCE - Campus Fortaleza";
        final var updateCecaneUseCase = new UpdateCecaneUseCase(repository);

        Mockito.when(
                repository.findById(cecane.id())
        ).thenReturn(Optional.of(cecane));

        final var input = new UpdateCecaneInput(
                cecane.id().value(),
                novoNome
        );

        // when
        final var result = updateCecaneUseCase.execute(input);

        // then
        assertNotNull(result);
        assertEquals(novoNome, result.nome());
        verify(repository, times(1)).update(cecane);

    }

    @Test
    @DisplayName("Deve lançar exceção quando não encontrar um Cecane")
    void shouldThrowExceptionWhenNotFound() {

        // given
        final var repository = mock(CecaneRepository.class);
        final var updateCecaneUseCase = new UpdateCecaneUseCase(repository);

        final var id = CecaneId.generate();

        final var input = new UpdateCecaneInput(
                id.value(),
                "IFCE - Campus Fortaleza"
        );

        final var expectedMessage = "Cecane com ID %s não encontrado".formatted(id.value());

        Mockito.when(
                repository.findById(id)
        ).thenReturn(Optional.empty());

        // when
        final var notFoundException = Assertions.assertThrows(NotFoundException.class, () -> updateCecaneUseCase.execute(input));

        // then
        assertNotNull(notFoundException);
        assertEquals(expectedMessage, notFoundException.getMessage());

    }

}