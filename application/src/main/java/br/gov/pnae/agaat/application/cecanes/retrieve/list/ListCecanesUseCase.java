package br.gov.pnae.agaat.application.cecanes.retrieve.list;

import br.gov.pnae.agaat.application.UseCase;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.pagination.Pagination;
import br.gov.pnae.agaat.domain.pagination.SearchQuery;
import jakarta.inject.Named;

import java.util.Objects;
@Named
public class ListCecanesUseCase extends UseCase<SearchQuery, Pagination<CecaneListOutput>> {
    private final CecaneRepository repository;

    public ListCecanesUseCase(final CecaneRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Pagination<CecaneListOutput> execute(final SearchQuery aQuery) {
        return this.repository.findAll(aQuery)
                .map(CecaneListOutput::fromAggregate);
    }

}
