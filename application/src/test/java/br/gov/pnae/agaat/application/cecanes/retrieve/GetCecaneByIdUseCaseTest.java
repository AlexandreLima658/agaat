package br.gov.pnae.agaat.application.cecanes.retrieve;

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
        GetCecaneByIdUseCase getCecanesByIdUseCase = new GetCecaneByIdUseCase(null);

        // then
        assertThrows(NullPointerException.class, () -> {
            getCecanesByIdUseCase.execute(1L);
        });
    }
    @Test
    void testExecuteWhenFound(){
        //given
        final Long id = 1L;
        CecaneRepository repository = Mockito.mock(CecaneRepository.class);
        Cecane cecane = new Cecane(CecaneId.from(id), new CecaneNome("Cecane 1"));
        // when
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(cecane));
        // then
        GetCecaneByIdOutput result = new GetCecaneByIdUseCase(repository).execute(id);
        assertEquals(1, result.id().value());

    }
    @Test
    void testExecuteWhenNotFound(){
       //given
        final Long id = 1L;
        CecaneRepository repository = Mockito.mock(CecaneRepository.class);
        // when
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());
        // then

        assertThrows(NotFoundException.class, () -> {
            new GetCecaneByIdUseCase(repository).execute(id);
        });

    }

}