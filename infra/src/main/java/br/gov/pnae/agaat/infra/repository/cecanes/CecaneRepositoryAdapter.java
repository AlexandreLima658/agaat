package br.gov.pnae.agaat.infra.repository.cecanes;

import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.pagination.Pagination;
import br.gov.pnae.agaat.domain.pagination.SearchQuery;
import br.gov.pnae.agaat.infra.jpa.cecanes.CecaneJpaEntity;
import br.gov.pnae.agaat.infra.jpa.cecanes.CecaneJpaMapper;
import br.gov.pnae.agaat.infra.jpa.cecanes.CecaneJpaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.fromString;

@Component
public class CecaneRepositoryAdapter implements CecaneRepository {

    private final CecaneJpaRepository repository;

    public CecaneRepositoryAdapter(final CecaneJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Cecane> findById(final CecaneId id) {
        return repository
                .findById(id.value())
                .map(CecaneJpaMapper::toAggregate);
    }

    @Override
    public void persist(final Cecane cecane) {
        repository.save(CecaneJpaMapper.toJpaEntity(cecane));
    }

    @Override
    public void update(final Cecane cecane) {
        repository.save(CecaneJpaMapper.toJpaEntity(cecane));
    }

    @Override
    public void deleteById(final CecaneId id) {
        repository.deleteById(id.value());
    }

    @Override
    public Pagination<Cecane> findAll(final SearchQuery aQuery) {

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
                pageResult.map(CecaneJpaMapper::toAggregate).toList()
        );

    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    private Specification<CecaneJpaEntity> assembleSpecification(final String terms) {
        final var searchTerms = "%" + terms.trim() + "%";

        return (root, query, builder) ->
                builder.or(
                        builder.like(root.get("id"), searchTerms),
                        builder.like(root.get("nome"), searchTerms)
                );
    }

}
