package br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.retrieve.by.id;

import br.gov.pnae.agaat.application.Presenter;
import br.gov.pnae.agaat.application.cecanes.retrieve.by.id.RetrieveCecaneByIdOutput;
import br.gov.pnae.agaat.domain.commons.exceptions.ErrorInfo;
import org.springframework.http.ResponseEntity;

public class RetrieveCecaneByIdHttpPresenter implements Presenter<RetrieveCecaneByIdOutput, Object> {

    @Override
    public ResponseEntity<?> present(final RetrieveCecaneByIdOutput output) {

        return ResponseEntity
                .ok()
                .body(
                        RetrieveCecaneByIdHttpResponse.mapperTo(output)
                );
    }

    @Override
    public ResponseEntity<?> present(final Throwable throwable) {
        return ResponseEntity.unprocessableEntity().body(
                new ErrorInfo(throwable.getMessage())
        );
    }

}
