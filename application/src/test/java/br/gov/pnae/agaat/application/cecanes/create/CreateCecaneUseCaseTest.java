package br.gov.pnae.agaat.application.cecanes.create;

import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.commons.validation.Either;
import br.gov.pnae.agaat.domain.commons.validation.handler.Notification;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@Tag("unitTest")
class CreateCecaneUseCaseTest {
    @Test
    void shouldWithValidInput(){
        //given
        CecaneRepository repository = mock(CecaneRepository.class);
        CreateCecaneUseCase createCecaneUseCase = new CreateCecaneUseCase(repository);
        CreateCecaneInput input = new CreateCecaneInput("Cecane 1");

        //when
        Either<Notification, CreateCecaneOutput> result = createCecaneUseCase.execute(input);

        //then
        assertTrue(result.isRight());
        verify(repository, times(1)).persist(any(Cecane.class));
    }
    @Test
    void shouldWithInvalidInput(){
        // given
        CecaneRepository repository = mock(CecaneRepository.class);
        CreateCecaneUseCase createCecaneUseCase = new CreateCecaneUseCase(repository);
        CreateCecaneInput input = new CreateCecaneInput("");

        // when
        Either<Notification, CreateCecaneOutput> result = createCecaneUseCase.execute(input);

        // then
        assertTrue(result.isLeft());
        verify(repository, never()).persist(any(Cecane.class));
    }
}