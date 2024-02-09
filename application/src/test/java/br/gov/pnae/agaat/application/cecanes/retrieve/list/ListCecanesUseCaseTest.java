package br.gov.pnae.agaat.application.cecanes.retrieve.list;

import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneNome;
import br.gov.pnae.agaat.domain.pagination.Pagination;
import br.gov.pnae.agaat.domain.pagination.SearchQuery;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("unitTest")
class ListCecanesUseCaseTest {
    @Test
    void shouldListCecanesUseCase() {
        //given
        ListCecanesUseCase listCecanesUseCase =
                new ListCecanesUseCase(mock(CecaneRepository.class));

        // then
        assertNotNull(listCecanesUseCase);

    }

    @Test
    void givenAQueryWhenExecuteThenReturnPagination() {
        //given
        final SearchQuery aQuery = new SearchQuery(1,10,"someTerms", "someSort");
        final CecaneRepository repository = mock(CecaneRepository.class);
        final Cecane cecane = new Cecane(CecaneId.from(1L), new CecaneNome("Cecane 1"));

        List<Cecane> items = List.of(cecane);
        Pagination<Cecane> pagination = new Pagination<>(1, 10, items.size(), items);
        when(repository.findAll(aQuery)).thenReturn(pagination);
        when(repository.findAll(aQuery)).thenReturn(pagination);
        // when
        Pagination<CecaneListOutput> result = new ListCecanesUseCase(repository).execute(aQuery);
        // then
        assertEquals(1, result.total());
    }

}