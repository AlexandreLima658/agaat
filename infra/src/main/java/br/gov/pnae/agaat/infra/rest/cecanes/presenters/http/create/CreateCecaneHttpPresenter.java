package br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.create;

import br.gov.pnae.agaat.application.Presenter;
import br.gov.pnae.agaat.application.cecanes.create.CreateCecaneOutput;
import br.gov.pnae.agaat.domain.commons.exceptions.ErrorInfo;
import org.springframework.http.ResponseEntity;

import static java.net.URI.create;

public class CreateCecaneHttpPresenter implements Presenter<CreateCecaneOutput, Object> {

    @Override
    public ResponseEntity<?> present(final CreateCecaneOutput output) {

        return ResponseEntity
                .created(create("/cecanes/" + output.id()))
                .body(
                        CreateCecaneHttpResponse.mapperTo(output)
                );
    }

    @Override
    public ResponseEntity<?> present(final Throwable throwable) {
        return ResponseEntity.unprocessableEntity().body(
                new ErrorInfo(throwable.getMessage())
        );
    }

}
