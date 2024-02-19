package br.gov.pnae.agaat.application.cecanes.command.delete;

import br.gov.pnae.agaat.application.IntegrationTest;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DeleteCecaneUseCaseTest extends IntegrationTest {
    @Test
    void shouldDeleteCecane() {

        // given
        final var cecaneId = CecaneId.generate();
        final var _mockRepository = Mockito.mock(CecaneRepository.class);
        final var deleteCecaneUseCase = new DeleteCecaneUseCase(_mockRepository);

        // when
        deleteCecaneUseCase.execute(cecaneId.value());

        // then
        Mockito.verify(_mockRepository, Mockito.times(1)).deleteById(cecaneId);

    }
}