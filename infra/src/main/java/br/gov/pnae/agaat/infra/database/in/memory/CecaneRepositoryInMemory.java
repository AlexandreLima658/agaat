package br.gov.pnae.agaat.infra.database.in.memory;

import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneRepository;
import br.gov.pnae.agaat.domain.cecanes.atributos.CecaneId;
import br.gov.pnae.agaat.domain.commons.exceptions.NotFoundException;
import br.gov.pnae.agaat.domain.pagination.Pagination;
import br.gov.pnae.agaat.domain.pagination.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CecaneRepositoryInMemory implements CecaneRepository {

    private final Map<Long, Cecane> cecanes;

    public CecaneRepositoryInMemory() {
        this.cecanes = new HashMap<>();
    }
    public class CecaneNameComparator implements Comparator<Cecane> {
        public int compare(Cecane str1, Cecane str2) {
            if (str1 == str2) {
                return 0;
            }
            if (str1 == null) {
                return -1;
            }
            if (str2 == null) {
                return 1;
            }

            return str1.nome().compareTo(str2.nome());
        }
    }

    @Override
    public Optional<Cecane> findById(final CecaneId id) {
        return Optional.ofNullable(cecanes.get(id.value()));
    }

    @Override
    public Optional<Cecane> findByNome(final String nome) {
        return cecanes.values().stream()
                .filter(cecane -> cecane.nome().equals(nome))
                .findFirst();
    }
    @Override
    public Pagination<Cecane> findAll(SearchQuery aQuery) {
        final CecaneNameComparator cecaneNameComparator = new CecaneNameComparator();
        final var cecanesList = cecanes.values().stream().toList().subList(aQuery.page(),aQuery.perPage());
        cecanesList.sort(cecaneNameComparator);
        return new Pagination<>(
                aQuery.page(),
                aQuery.perPage(),
                cecanes.size(),
                cecanesList
        );

    }
    @Override
    public Cecane persist(Cecane cecane) {
        cecanes.put(cecane.id().value(), cecane);
        return cecane;
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



}
