//package br.gov.pnae.agaat.application.cecanes.retrieve.list;
//
//import br.gov.pnae.agaat.domain.cecanes.Cecane;
//import br.gov.pnae.agaat.domain.cecanes.CecaneFactory;
//import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
//import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
//import br.gov.pnae.agaat.domain.pagination.Pagination;
//import br.gov.pnae.agaat.domain.pagination.SearchQuery;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@Tag("unitTest")
//class RetrieveCecanesByFilterUseCaseTest {
//    @Test
//    void shouldListCecanesUseCase() {
//        //given
//        RetrieveCecanesByFilterUseCase listCecanesUseCase =
//                new RetrieveCecanesByFilterUseCase(mock(CecaneRepository.class));
//
//        // then
//        assertNotNull(listCecanesUseCase);
//
//    }
//
//    @Test
//    void givenAQueryWhenExecuteThenReturnPagination() {
//        //given
//        final SearchQuery aQuery = new SearchQuery(1,10,"someTerms", "someSort");
//        final CecaneRepository repository = mock(CecaneRepository.class);
//        final Cecane cecane = CecaneFactory.create(CecaneId.from(1L), "Cecane 1").right();
//
//        List<Cecane> items = List.of(cecane);
//        Pagination<Cecane> pagination = new Pagination<>(1, 10, items.size(), items);
//        when(repository.findAll(aQuery)).thenReturn(pagination);
//        when(repository.findAll(aQuery)).thenReturn(pagination);
//        // when
//        Pagination<RetrieveCecanesByFilterOutput> result = new RetrieveCecanesByFilterUseCase(repository).execute(aQuery);
//        // then
//        assertEquals(1, result.total());
//    }
//
//}