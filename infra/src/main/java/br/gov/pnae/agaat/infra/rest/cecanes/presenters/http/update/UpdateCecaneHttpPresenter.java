package br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.update;

import br.gov.pnae.agaat.application.Presenter;
import br.gov.pnae.agaat.application.cecanes.update.UpdateCecaneOutput;
import br.gov.pnae.agaat.domain.commons.exceptions.ErrorInfo;
import org.springframework.http.ResponseEntity;

public class UpdateCecaneHttpPresenter implements Presenter<UpdateCecaneOutput, Object> {

    @Override
    public ResponseEntity<?> present(final UpdateCecaneOutput output) {

        return ResponseEntity
                .ok()
                .body(UpdateCecaneHttpResponse.mapperTo(output));

    }

    @Override
    public ResponseEntity<?> present(final Throwable throwable) {
        return ResponseEntity.unprocessableEntity().body(
                new ErrorInfo(throwable.getMessage())
        );
    }

}
