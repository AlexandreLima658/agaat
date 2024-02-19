package br.gov.pnae.agaat.infra.repository.cecanes;

import br.gov.pnae.agaat.application.cecanes.query.filter.RetrieveCecanesByFilterGateway;
import br.gov.pnae.agaat.application.cecanes.query.filter.RetrieveCecanesByFilterOutput;
import br.gov.pnae.agaat.domain.pagination.Pagination;
import br.gov.pnae.agaat.domain.pagination.SearchQuery;
import br.gov.pnae.agaat.infra.jpa.cecanes.CecaneJpaEntity;
import br.gov.pnae.agaat.infra.jpa.cecanes.CecaneJpaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.fromString;

@Component
public class RetrieveCecaneByFilterGatewayAdapter implements RetrieveCecanesByFilterGateway {

    private final CecaneJpaRepository repository;

    public RetrieveCecaneByFilterGatewayAdapter(final CecaneJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pagination<RetrieveCecanesByFilterOutput> execute(final SearchQuery aQuery) {

        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(fromString(aQuery.sortDirection()), aQuery.sortBy())
        );

        final var specifications =
                Optional.ofNullable(aQuery.terms())
                        .filter(str -> !str.isBlank())
                        .map(this::assembleSpecification)
                        .orElse((root, query, builder) -> builder.conjunction());

        final var pageResult =
                this.repository.findAll(specifications, page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(jpa -> new RetrieveCecanesByFilterOutput(jpa.getId(), jpa.getNome())).toList()
        );
    }

    private Specification<CecaneJpaEntity> assembleSpecification(final String terms) {
        final var searchTerms = "%" + terms.trim().toLowerCase() + "%";

        return (root, query, builder) ->
                builder.or(
                        builder.like(builder.lower(root.get("nome")), searchTerms, '\\')
                );
    }
}
