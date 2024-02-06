package br.gov.pnae.agaat.application.cecanes.create;

import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

@Tag("unitTest")
class CreateCecaneUseCaseTest {
    @Test
    void givenCreateCecaneUseCase_whenCreateCecane_thenCecaneIsCreated() {
       //given
       CecaneRepository mockCecaneRepository = mock(CecaneRepository.class);
       CreateCecaneUseCase createCecaneUseCase = new CreateCecaneUseCase(mockCecaneRepository);

         Input input = new Input("Cecane 1");

            Cecane cecane = input.toAggregate();
            when(mockCecaneRepository.persist(cecane)).thenReturn(cecane);
            //when
            Output output = createCecaneUseCase.execute(input);
            //then
            verify(mockCecaneRepository, times(0)).persist(cecane);

    }
}