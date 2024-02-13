package br.gov.pnae.agaat.application.cecanes.delete;

import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
@Tag("unitTest")
class DeleteCecaneUseCaseTest {
    @Test
    void shouldDeleteCecane() {
        // given
        final var MockRepository = Mockito.mock(CecaneRepository.class);
        final var deleteCecaneUseCase = new DeleteCecaneUseCase(MockRepository);
        // when
        deleteCecaneUseCase.execute(1L);
        // then
        Mockito.verify(MockRepository).deleteById(Mockito.any());

    }
}