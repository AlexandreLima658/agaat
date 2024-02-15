package br.gov.pnae.agaat.application.cecanes.retrieve.get;

import br.gov.pnae.agaat.application.cecanes.retrieve.by.id.RetrieveCecaneByIdUseCase;
import br.gov.pnae.agaat.domain.cecanes.CecaneFactory;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.commons.exceptions.NotFoundException;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@Tag("integrationTest")
class RetrieveCecaneByIdUseCaseTest {

    private final CecaneRepository repository = mock(CecaneRepository.class);

    @BeforeEach
    void setUp() {
        Mockito.reset(repository);
    }

    @Test
    @DisplayName("Deve retornar um Cecane pelo id")
    void shouldReturnCecaneById() {

        // given
        final var cecane = CecaneFactory.create(
                "IFCE - Campus Acaraú"
        );

        Mockito.when(
                repository.findById(cecane.id())
        ).thenReturn(Optional.of(cecane));

        // when
        final var result = new RetrieveCecaneByIdUseCase(repository).execute(cecane.id().value());

        // then
        assertNotNull(result);
        assertEquals(cecane.id().value(), result.id());
        assertEquals(cecane.nome(), result.nome());

    }

    @Test
    @DisplayName("Deve lançar exceção quando não encontrar um Cecane")
    void shouldThrowExceptionWhenNotFound() {

        // given
        final var cecaneId = CecaneId.generate();

        Mockito.when(
                repository.findById(cecaneId)
        ).thenReturn(Optional.empty());

        final var expectedErrorMessage = "Cecane com ID %s não encontrado".formatted(cecaneId.value());

        // when
        final var error = Assertions.assertThrows(
                NotFoundException.class,
                () -> new RetrieveCecaneByIdUseCase(repository).execute(cecaneId.value())
        );

        // then
        assertEquals(expectedErrorMessage, error.getMessage());

    }

}