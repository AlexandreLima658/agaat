package br.gov.pnae.agaat.application.cecanes.retrieve.get;

import br.gov.pnae.agaat.application.cecanes.retrieve.get.GetCecaneByIdOutput;
import br.gov.pnae.agaat.application.cecanes.retrieve.get.GetCecaneByIdUseCase;
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
@Tag("unitTest")
class GetCecaneByIdUseCaseTest {
    @Test
    void shouldGetCecaneUseCase(){
        //given
        // Require NonNull
        GetCecaneByIdUseCase getCecanesByIdUseCase =
                new GetCecaneByIdUseCase(Mockito.mock(CecaneRepository.class));

        // then
        assertNotNull(getCecanesByIdUseCase);
    }
    @Test
    void testExecuteWhenFound(){
        //given
        final Long id = 1L;
        final CecaneId cecaneId = CecaneId.from(id);
        CecaneRepository repository = Mockito.mock(CecaneRepository.class);
        Cecane cecane = new Cecane(CecaneId.from(id), new CecaneNome("Cecane 1"));
        // when
        Mockito.when(repository.findById(cecaneId)).thenReturn(Optional.of(cecane));
        // then
        GetCecaneByIdOutput result = new GetCecaneByIdUseCase(repository).execute(id);
        assertEquals(1, result.id().value());

    }
    @Test
    void testExecuteWhenNotFound(){
       //given
        final Long id = 1L;
        final CecaneId cecaneId = CecaneId.from(id);
        CecaneRepository repository = Mockito.mock(CecaneRepository.class);
        // when
        Mockito.when(repository.findById(cecaneId)).thenReturn(Optional.empty());
        // then

        assertThrows(NotFoundException.class, () -> {
            new GetCecaneByIdUseCase(repository).execute(id);
        });

    }

}