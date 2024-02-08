package br.gov.pnae.agaat.application.cecanes.update;

import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneNome;
import br.gov.pnae.agaat.domain.commons.exceptions.NotFoundException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@Tag("unitTest")
class UpdateCecaneUseCaseTest {
    @Test
    void shouldUpdateCecaneUseCase(){
        //given
        // Require NonNull
        UpdateCecaneUseCase updateCecaneUseCase =
                new UpdateCecaneUseCase(mock(CecaneRepository.class));

        // then
        assertNotNull(updateCecaneUseCase);
    }
    @Test
    void shouldUpdateCecaneOutputExecute(){
        //given
        CecaneRepository cecaneRepository = mock(CecaneRepository.class);
        final CecaneNome CecaneName = new CecaneNome("CecaneName");

        // Require NonNull
        UpdateCecaneUseCase updateCecaneUseCase =
                new UpdateCecaneUseCase(cecaneRepository);

        //when
        Cecane cecane = new Cecane(CecaneId.from(1L), CecaneName);
        Mockito.when(cecaneRepository.findById(CecaneId.from(1L))).thenReturn(java.util.Optional.of(cecane));

        //then
        assertNotNull(updateCecaneUseCase.execute(UpdateCecaneCommand.with(1L, "CecaneName")));

    }

    @Test
    void testExecuteWhenNotFound() {
        //given
        final Long id = 1L;
        final CecaneId cecaneId = CecaneId.from(id);
        CecaneRepository repository = Mockito.mock(CecaneRepository.class);
        // when
        Mockito.when(repository.findById(cecaneId)).thenReturn(Optional.empty());
        // then
        final UpdateCecaneCommand command = UpdateCecaneCommand.with(1L, "CecaneName");

        assertThrows(NotFoundException.class, () -> {
            new UpdateCecaneUseCase(repository).execute(command);
        });
    }
}