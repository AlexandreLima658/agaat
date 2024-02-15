package br.gov.pnae.agaat.application.cecanes.retrieve.by.filter;

import br.gov.pnae.agaat.application.UseCase;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.pagination.Pagination;
import br.gov.pnae.agaat.domain.pagination.SearchQuery;
import jakarta.inject.Named;

import java.util.Objects;

@Named
public final class RetrieveCecanesByFilterUseCase extends UseCase<SearchQuery, Pagination<RetrieveCecanesByFilterOutput>> {
    private final CecaneRepository repository;

    public RetrieveCecanesByFilterUseCase(final CecaneRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    protected Pagination<RetrieveCecanesByFilterOutput> execute(final SearchQuery aQuery) {
        return this.repository
                .findAll(aQuery)
                .map(RetrieveCecanesByFilterOutput::fromAggregate);
    }

}
