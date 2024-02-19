package br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.update;

import br.gov.pnae.agaat.application.Presenter;
import br.gov.pnae.agaat.application.cecanes.command.update.UpdateCecaneOutput;
import br.gov.pnae.agaat.domain.commons.exceptions.ErrorInfo;
import org.springframework.http.ResponseEntity;

public class UpdateCecaneHttpPresenter implements Presenter<UpdateCecaneOutput, Object> {

    @Override
    public Object present(final UpdateCecaneOutput output) {

        return UpdateCecaneHttpResponse.mapperTo(output);

    }

    @Override
    public ResponseEntity<?> present(final Throwable throwable) {
        return ResponseEntity.unprocessableEntity()
                .body(
                        new ErrorInfo(throwable.getMessage())
                );
    }

}
