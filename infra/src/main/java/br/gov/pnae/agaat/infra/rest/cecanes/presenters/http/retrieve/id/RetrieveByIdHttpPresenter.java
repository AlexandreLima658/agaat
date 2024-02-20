package br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.retrieve.id;

import br.gov.pnae.agaat.application.Presenter;
import br.gov.pnae.agaat.application.cecanes.query.id.RetrieveCecaneByIdOutput;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class RetrieveByIdHttpPresenter implements Presenter<RetrieveCecaneByIdOutput, Object>{
    @Override
    public Object present(final RetrieveCecaneByIdOutput output) {
        return ResponseEntity.ok(
                new RetrieveByIdHttpResponse(
                        output.id(),
                        output.nome()
                )
        );
    }

    @Override
    public Object present(Throwable throwable) {
        return null;
    }
}
