package br.gov.pnae.agaat.application.cecanes.query.id;

import br.gov.pnae.agaat.application.IntegrationTest;
import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneFactory;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.commons.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class RetrieveCecaneByIdGatewayTest extends IntegrationTest {

    private final RetrieveCecaneByIdGateway gateway = mock(RetrieveCecaneByIdGateway.class);

    @BeforeEach
    void setUp() {
        Mockito.reset(gateway);
    }

    @Test
    @DisplayName("Deve retornar um Cecane pelo id")
    void shouldReturnCecaneById() {

        // given
        final var cecane = CecaneFactory.create(
                "IFCE - Campus Acaraú"
        );

        Mockito.when(
                gateway.execute(cecane.id().value())
        ).thenReturn(new RetrieveCecaneByIdOutput(cecane.id().value(), cecane.nome()));

        // when
        final var result = gateway.execute(cecane.id().value());

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
                gateway.execute(cecaneId.value())
        ).thenThrow(NotFoundException.with(Cecane.class, cecaneId));

        final var expectedErrorMessage = "Cecane com ID %s não encontrado".formatted(cecaneId.value());

        // when
        final var error = Assertions.assertThrows(
                NotFoundException.class,
                () -> gateway.execute(cecaneId.value())
        );

        // then
        assertEquals(expectedErrorMessage, error.getMessage());

    }

}