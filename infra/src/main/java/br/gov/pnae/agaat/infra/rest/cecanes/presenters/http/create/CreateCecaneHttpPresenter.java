package br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.create;

import br.gov.pnae.agaat.application.Presenter;
import br.gov.pnae.agaat.application.cecanes.command.create.CreateCecaneOutput;
import br.gov.pnae.agaat.domain.commons.exceptions.ErrorInfo;
import org.springframework.http.ResponseEntity;

import static java.net.URI.create;

public class CreateCecaneHttpPresenter implements Presenter<CreateCecaneOutput, Object> {

    @Override
    public Object present(final CreateCecaneOutput output) {

        return ResponseEntity
                .created(create("/cecanes/" + output.id()))
                .body(
                        CreateCecaneHttpResponse.from(output)
                );
    }

    @Override
    public Object present(final Throwable throwable) {
        return ResponseEntity.unprocessableEntity().body(
                new ErrorInfo(throwable.getMessage())
        );
    }

}
