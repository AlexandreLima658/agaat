package br.gov.pnae.agaat.infra.database.in.memory;

import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.commons.exceptions.NotFoundException;
import br.gov.pnae.agaat.domain.pagination.Pagination;
import br.gov.pnae.agaat.domain.pagination.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CecaneRepositoryInMemory implements CecaneRepository {
    private final Map<Long, Cecane> cecanes;

    public CecaneRepositoryInMemory() {
        this.cecanes = new HashMap<>();
    }

    @Override
    public Optional<Cecane> findById(final CecaneId id) {
        return Optional.ofNullable(cecanes.get(id.value()));
    }

    @Override
    public Pagination<Cecane> findAll(final SearchQuery aQuery) {

        var cecanes = this.cecanes.values().stream()
                .filter(cecane -> aQuery.terms() == null || cecane.nome().toLowerCase().contains(aQuery.terms().toLowerCase()))
                .toList();

        if (aQuery.sort() != null) {
            if (aQuery.sort().equals("nome")) {
                cecanes = cecanes.stream().sorted(Comparator.comparing(Cecane::nome)).toList();
            }
        }

        var cecanesList = new ArrayList<>(cecanes).subList(aQuery.page(), aQuery.perPage());

        return new Pagination<>(
                aQuery.page(),
                aQuery.perPage(),
                cecanes.size(),
                cecanesList
        );
    }

    @Override
    public void persist(final Cecane cecane) {
        cecanes.put(cecane.id().value(), cecane);
    }

    @Override
    public void update(final Cecane cecane) {
        final var cecaneId = cecane.id();

        final var persistedCecane = this.findById(cecaneId)
                .orElseThrow(() -> NotFoundException.with(Cecane.class, cecaneId));

        cecanes.put(cecaneId.value(), cecane);
    }

    @Override
    public void deleteById(final CecaneId id) {
        final var persistedCecane = this.findById(id)
                .orElseThrow(() -> NotFoundException.with(Cecane.class, id));

        cecanes.remove(id.value());
    }

    public void deleteAll() {
        cecanes.clear();
    }

}
