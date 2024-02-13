package br.gov.pnae.agaat.infra.rest.cecanes;

import br.gov.pnae.agaat.application.cecanes.create.CreateCecaneInput;
import br.gov.pnae.agaat.application.cecanes.create.CreateCecaneUseCase;
import br.gov.pnae.agaat.application.cecanes.delete.DeleteCecaneUseCase;
import br.gov.pnae.agaat.application.cecanes.retrieve.by.id.RetrieveCecaneByIdUseCase;
import br.gov.pnae.agaat.application.cecanes.retrieve.by.filter.RetrieveCecanesByFilterUseCase;
import br.gov.pnae.agaat.application.cecanes.update.UpdateCecaneUseCase;
import br.gov.pnae.agaat.domain.pagination.SearchQuery;
import br.gov.pnae.agaat.infra.rest.cecanes.models.UpdateCecaneHttpRequest;
import br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.create.CreateCecaneHttpPresenter;
import br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.retrieve.by.filter.RetrieveCecaneByFilterHttpPresenter;
import br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.retrieve.by.id.RetrieveCecaneByIdHttpPresenter;
import br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.update.UpdateCecaneHttpPresenter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class CecaneController implements CecaneAPI {
    private final CreateCecaneUseCase createCecaneUseCase;
    private final RetrieveCecaneByIdUseCase retrieveCecaneByIdUseCase;
    private final UpdateCecaneUseCase updateCecaneUseCase;
    private final DeleteCecaneUseCase deleteCecaneUseCase;
    private final RetrieveCecanesByFilterUseCase retrieveCecanesByFilterUseCase;

    public CecaneController(
            final CreateCecaneUseCase createCecaneUseCase,
            final RetrieveCecaneByIdUseCase retrieveCecaneByIdUseCase,
            final UpdateCecaneUseCase updateCecaneUseCase,
            final DeleteCecaneUseCase deleteCecaneUseCase,
            final RetrieveCecanesByFilterUseCase retrieveCecanesByFilterUseCase
    ) {
        this.createCecaneUseCase = Objects.requireNonNull(createCecaneUseCase);
        this.retrieveCecaneByIdUseCase = Objects.requireNonNull(retrieveCecaneByIdUseCase);
        this.updateCecaneUseCase = Objects.requireNonNull(updateCecaneUseCase);
        this.deleteCecaneUseCase = Objects.requireNonNull(deleteCecaneUseCase);
        this.retrieveCecanesByFilterUseCase = Objects.requireNonNull(retrieveCecanesByFilterUseCase);
    }

    @Override
    public Object create(@RequestBody final CreateCecaneInput request) {

        final var presenter = new CreateCecaneHttpPresenter();

        return this.createCecaneUseCase.execute(
                request,
                presenter
        );

    }


    @Override
    public Object retrieveById(final Long id) {

        final var presenter = new RetrieveCecaneByIdHttpPresenter();

        return this.retrieveCecaneByIdUseCase.execute(id, presenter);

    }

    @Override
    public Object retrieveByFilter(
            final String search,
            final int page,
            final int perPage,
            final String sort) {

        final var query = new SearchQuery(
                page,
                perPage,
                search,
                sort
        );

        final var presenter = new RetrieveCecaneByFilterHttpPresenter();

        return retrieveCecanesByFilterUseCase.execute(query, presenter);
    }

    @Override
    public Object update(final Long id, final UpdateCecaneHttpRequest request) {

        final var presenter = new UpdateCecaneHttpPresenter();

        final var updateInput = request.toInput(id);

        return this.updateCecaneUseCase.execute(updateInput, presenter);
    }

    @Override
    public void delete(final Long id) {
        this.deleteCecaneUseCase.execute(id);
    }
}
