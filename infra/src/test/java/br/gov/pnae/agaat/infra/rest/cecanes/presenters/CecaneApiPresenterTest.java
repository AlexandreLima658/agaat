package br.gov.pnae.agaat.infra.rest.cecanes.presenters;

import br.gov.pnae.agaat.application.cecanes.retrieve.get.GetCecaneByIdOutput;
import br.gov.pnae.agaat.application.cecanes.retrieve.list.CecaneListOutput;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@Tag("unitTest")
class CecaneApiPresenterTest {
    @Test
    void shouldPresentCecaneApiOutput() {
        // Given
        final CecaneId cecaneId = mock(CecaneId.class);
        var output = new GetCecaneByIdOutput(cecaneId, "Cecane Name");
        // When
        var result = CecaneApiPresenter.present(output);
        // Then
        assertNotNull(result);
    }

    @Test
    void shouldPresentCecaneListResponse() {
        // Given
        final CecaneId cecaneId = mock(CecaneId.class);
        var output = new CecaneListOutput(cecaneId, "Cecane Name");
        // When
        var result = CecaneApiPresenter.present(output);
        // Then
        assertNotNull(result);
    }
}