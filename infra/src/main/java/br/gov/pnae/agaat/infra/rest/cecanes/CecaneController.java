package br.gov.pnae.agaat.infra.rest.cecanes;

import br.gov.pnae.agaat.application.cecanes.CreateCecaneUseCase;
import br.gov.pnae.agaat.domain.commons.exceptions.DomainException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/cecanes")
public class CecaneController {

    private final CreateCecaneUseCase useCase;

    public CecaneController(final CreateCecaneUseCase useCase) {
        this.useCase = Objects.requireNonNull(useCase);
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody final CreateCecaneUseCase.Input input) {

        try {
            final var output = useCase.execute(input);
            final var uri = URI.create("/cecanes/" + output.id());

            return ResponseEntity.created(uri).body(output);
        } catch (final DomainException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
