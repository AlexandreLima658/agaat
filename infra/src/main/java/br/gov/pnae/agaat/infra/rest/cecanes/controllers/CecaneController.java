package br.gov.pnae.agaat.infra.rest.cecanes.controllers;

import br.gov.pnae.agaat.application.cecanes.create.CreateCecaneUseCase;
import br.gov.pnae.agaat.application.cecanes.create.Input;
import br.gov.pnae.agaat.application.cecanes.retrieve.GetCecaneByIdUseCase;
import br.gov.pnae.agaat.domain.commons.exceptions.DomainException;
import br.gov.pnae.agaat.infra.rest.cecanes.CecaneAPI;
import br.gov.pnae.agaat.infra.rest.cecanes.models.CecaneResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;

@RestController
public class CecaneController  implements CecaneAPI {

    private final CreateCecaneUseCase createCecaneUseCase;
    private final GetCecaneByIdUseCase getCecaneByIdUseCase;

    public CecaneController(final CreateCecaneUseCase createCecaneUseCase,
                            final GetCecaneByIdUseCase getCecaneByIdUseCase) {
        this.createCecaneUseCase = Objects.requireNonNull(createCecaneUseCase);
        this.getCecaneByIdUseCase = Objects.requireNonNull(getCecaneByIdUseCase);
    }

    @Override
    public ResponseEntity<?> create(@RequestBody final Input input) {
        try {
            final var output = createCecaneUseCase.execute(input);
            final var uri = URI.create("/cecanes/" + output.id());

            return ResponseEntity.created(uri).body(output);
        } catch (final DomainException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    public CecaneResponse getById(final Long id) {
        try {
            final var output = getCecaneByIdUseCase.execute(id);
            final var uri = URI.create("/cecanes/" + output.id());

            return new CecaneResponse(output.id(), output.nome());
        } catch (final DomainException e) {
            return null; // ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
