package br.gov.pnae.agaat.domain.pagination;

public record SearchQuery(
        int page,
        int perPage,
        String terms,
        String sortBy,
        String sortDirection
) {
}
