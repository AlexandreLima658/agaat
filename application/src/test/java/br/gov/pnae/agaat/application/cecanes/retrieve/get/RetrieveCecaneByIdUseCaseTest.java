//package br.gov.pnae.agaat.application.cecanes.retrieve.get;
//
//import br.gov.pnae.agaat.domain.cecanes.Cecane;
//import br.gov.pnae.agaat.domain.cecanes.CecaneFactory;
//import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
//import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@Tag("unitTest")
//class RetrieveCecaneByIdUseCaseTest {
//    @Test
//    void shouldGetCecaneUseCase() {
//        //given
//        // Require NonNull
//        RetrieveCecaneByIdUseCase getCecanesByIdUseCase =
//                new RetrieveCecaneByIdUseCase(Mockito.mock(CecaneRepository.class));
//
//        // then
//        assertNotNull(getCecanesByIdUseCase);
//    }
//
//    @Test
//    void testExecuteWhenFound() {
//        //given
//        final Long id = 1L;
//        final CecaneId cecaneId = CecaneId.from(id);
//        CecaneRepository repository = Mockito.mock(CecaneRepository.class);
//        Cecane cecane = CecaneFactory.create(CecaneId.from(id), "Cecane 1").right();
//        // when
//        Mockito.when(repository.findById(cecaneId)).thenReturn(Optional.of(cecane));
//        // then
//        RetrieveCecaneByIdOutput result = new RetrieveCecaneByIdUseCase(repository).execute(id).get();
//        assertEquals(1, result.id());
//
//    }
//
//    @Test
//    void testExecuteWhenNotFound() {
//        //given
//        final Long id = 1L;
//        final CecaneId cecaneId = CecaneId.from(id);
//        CecaneRepository repository = Mockito.mock(CecaneRepository.class);
//        // when
//        Mockito.when(repository.findById(cecaneId)).thenReturn(Optional.empty());
//
//        final var useCase = new RetrieveCecaneByIdUseCase(repository).execute(id);
//        // then
//
//        assertNotNull(useCase);
//        assertTrue(useCase.isEmpty());
//
//
//    }
//
//}