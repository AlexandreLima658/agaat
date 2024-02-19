package br.gov.pnae.agaat.infra.rest.cecanes;

import br.gov.pnae.agaat.application.cecanes.command.create.CreateCecaneInput;
import br.gov.pnae.agaat.application.cecanes.command.create.CreateCecaneUseCase;
import br.gov.pnae.agaat.application.cecanes.command.delete.DeleteCecaneUseCase;
import br.gov.pnae.agaat.application.cecanes.command.update.UpdateCecaneUseCase;
import br.gov.pnae.agaat.application.cecanes.query.filter.RetrieveCecanesByFilterGateway;
import br.gov.pnae.agaat.application.cecanes.query.id.RetrieveCecaneByIdGateway;
import br.gov.pnae.agaat.domain.commons.exceptions.ErrorInfo;
import br.gov.pnae.agaat.domain.pagination.SearchQuery;
import br.gov.pnae.agaat.infra.rest.cecanes.models.UpdateCecaneHttpRequest;
import br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.create.CreateCecaneHttpPresenter;
import br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.update.UpdateCecaneHttpPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.UUID;

@RestController
public class CecaneController implements CecaneAPI {
    private final CreateCecaneUseCase createCecaneUseCase;
    private final RetrieveCecaneByIdGateway retrieveCecaneByIdGateway;
    private final UpdateCecaneUseCase updateCecaneUseCase;
    private final DeleteCecaneUseCase deleteCecaneUseCase;
    private final RetrieveCecanesByFilterGateway retrieveCecanesByFilterGateway;

    public CecaneController(
            final CreateCecaneUseCase createCecaneUseCase,
            final RetrieveCecaneByIdGateway retrieveCecaneByIdGateway,
            final UpdateCecaneUseCase updateCecaneUseCase,
            final DeleteCecaneUseCase deleteCecaneUseCase,
            final RetrieveCecanesByFilterGateway retrieveCecanesByFilterGateway
    ) {
        this.createCecaneUseCase = Objects.requireNonNull(createCecaneUseCase);
        this.retrieveCecaneByIdGateway = Objects.requireNonNull(retrieveCecaneByIdGateway);
        this.updateCecaneUseCase = Objects.requireNonNull(updateCecaneUseCase);
        this.deleteCecaneUseCase = Objects.requireNonNull(deleteCecaneUseCase);
        this.retrieveCecanesByFilterGateway = Objects.requireNonNull(retrieveCecanesByFilterGateway);
    }

    @Override
    public Object create(@RequestBody final CreateCecaneInput request) {

        return this.createCecaneUseCase.execute(
                request,
                new CreateCecaneHttpPresenter()
        );

    }


    @Override
    public ResponseEntity<?> retrieveById(final UUID id) {
        try {
            return ResponseEntity.ok(this.retrieveCecaneByIdGateway.execute(id));
        } catch (final Throwable e) {
            return ResponseEntity.badRequest().body(new ErrorInfo(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> retrieveByFilter(
            final int page,
            final int perPage,
            final String terms,
            final String sort,
            final String direction
    ) {

        try {
            final var query = new SearchQuery(
                    page,
                    perPage,
                    terms,
                    sort,
                    direction
            );

            return ResponseEntity.ok(retrieveCecanesByFilterGateway.execute(query));

        } catch (final Throwable e) {
            return ResponseEntity.badRequest().body(new ErrorInfo(e.getMessage()));
        }

    }

    @Override
    public Object update(final UUID id, final UpdateCecaneHttpRequest request) {

        final var presenter = new UpdateCecaneHttpPresenter();

        final var updateInput = request.toInput(id);

        return this.updateCecaneUseCase.execute(updateInput, presenter);
    }

    @Override
    public void delete(final UUID id) {
        this.deleteCecaneUseCase.execute(id);
    }
}
