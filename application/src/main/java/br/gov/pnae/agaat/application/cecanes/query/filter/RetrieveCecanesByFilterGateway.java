package br.gov.pnae.agaat.application.cecanes.query.filter;

import br.gov.pnae.agaat.domain.pagination.Pagination;
import br.gov.pnae.agaat.domain.pagination.SearchQuery;

public interface RetrieveCecanesByFilterGateway {

    Pagination<RetrieveCecanesByFilterOutput> execute(final SearchQuery aQuery);

}
