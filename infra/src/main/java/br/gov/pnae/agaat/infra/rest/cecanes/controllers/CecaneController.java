package br.gov.pnae.agaat.infra.rest.cecanes.controllers;

import br.gov.pnae.agaat.application.cecanes.create.CreateCecaneUseCase;
import br.gov.pnae.agaat.application.cecanes.create.CreateCecaneInput;
import br.gov.pnae.agaat.application.cecanes.create.CreateCecaneOutput;
import br.gov.pnae.agaat.application.cecanes.delete.DeleteCecaneUseCase;
import br.gov.pnae.agaat.application.cecanes.retrieve.get.GetCecaneByIdUseCase;
import br.gov.pnae.agaat.application.cecanes.retrieve.list.ListCecanesUseCase;
import br.gov.pnae.agaat.application.cecanes.update.UpdateCecaneCommand;
import br.gov.pnae.agaat.application.cecanes.update.UpdateCecaneUseCase;
import br.gov.pnae.agaat.domain.commons.validation.handler.Notification;
import br.gov.pnae.agaat.domain.pagination.Pagination;
import br.gov.pnae.agaat.domain.pagination.SearchQuery;
import br.gov.pnae.agaat.infra.rest.cecanes.CecaneAPI;
import br.gov.pnae.agaat.infra.rest.cecanes.models.CecaneApiOutput;
import br.gov.pnae.agaat.infra.rest.cecanes.models.CreateCecaneRequest;
import br.gov.pnae.agaat.infra.rest.cecanes.presenters.CecaneApiPresenter;
import br.gov.pnae.agaat.infra.rest.cecanes.models.UpdateCecaneRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

@RestController
public class CecaneController implements CecaneAPI {
    private final CreateCecaneUseCase createCecaneUseCase;
    private final GetCecaneByIdUseCase getCecaneByIdUseCase;
    private final UpdateCecaneUseCase updateCecaneUseCase;
    private final DeleteCecaneUseCase deleteCecaneUseCase;
    private final ListCecanesUseCase listCecanesUseCase;

    public CecaneController(
            final CreateCecaneUseCase createCecaneUseCase,
            final GetCecaneByIdUseCase getCecaneByIdUseCase,
            final UpdateCecaneUseCase updateCecaneUseCase,
            final DeleteCecaneUseCase deleteCecaneUseCase,
            final ListCecanesUseCase listCecanesUseCase
    ) {
        this.createCecaneUseCase = Objects.requireNonNull(createCecaneUseCase);
        this.getCecaneByIdUseCase = Objects.requireNonNull(getCecaneByIdUseCase);
        this.updateCecaneUseCase = Objects.requireNonNull(updateCecaneUseCase);
        this.deleteCecaneUseCase = Objects.requireNonNull(deleteCecaneUseCase);
        this.listCecanesUseCase = Objects.requireNonNull(listCecanesUseCase);
    }

    @Override
    public ResponseEntity<?> create(@RequestBody final CreateCecaneRequest input) {
        final var command = new CreateCecaneInput(input.name());

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);
        final Function<CreateCecaneOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/cecanes/" + output.id())).body(output);

        return createCecaneUseCase.execute(command).fold(onError, onSuccess);
        /*
        try {
            final var command = new Input(input.name());
            final var output = createCecaneUseCase.execute(command);
            final var uri = URI.create("/cecanes/" + output.id());

            return ResponseEntity.created(uri).body(output);
        } catch (final DomainException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }*/
    }

    @Override
    public CecaneApiOutput getById(final Long id) {
        return CecaneApiPresenter.present(this.getCecaneByIdUseCase.execute(id));
    }

    @Override
    public Pagination<?> listCecanes(
            final String search,
            final int page,
            final int perPage,
            final String sort)
    {
        return listCecanesUseCase.execute(new SearchQuery(page, perPage, search, sort))
                .map(CecaneApiPresenter::present);
    }

    @Override
    public ResponseEntity<?> update(final Long id, final UpdateCecaneRequest input) {
        final var command = UpdateCecaneCommand.with(id, input.name());

        final var output = this.updateCecaneUseCase.execute(command);

        return ResponseEntity.ok().body(output);
    }

    @Override
    public void delete(final Long id) {
        this.deleteCecaneUseCase.execute(id);
    }
}
