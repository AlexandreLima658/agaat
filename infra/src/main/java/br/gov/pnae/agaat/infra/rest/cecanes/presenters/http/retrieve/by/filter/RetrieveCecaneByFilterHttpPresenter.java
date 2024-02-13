package br.gov.pnae.agaat.infra.rest.cecanes.presenters.http.retrieve.by.filter;

import br.gov.pnae.agaat.application.Presenter;
import br.gov.pnae.agaat.application.cecanes.retrieve.by.filter.RetrieveCecanesByFilterOutput;
import br.gov.pnae.agaat.domain.commons.exceptions.ErrorInfo;
import br.gov.pnae.agaat.domain.pagination.Pagination;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;

public class RetrieveCecaneByFilterHttpPresenter implements Presenter<Pagination<RetrieveCecanesByFilterOutput>, Object> {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(RetrieveCecaneByFilterHttpPresenter.class);

    @Override
    public ResponseEntity<Pagination<?>> present(final Pagination<RetrieveCecanesByFilterOutput> output) {

        return ResponseEntity
                .ok()
                .body(
                        output.map(RetrieveCecaneByFilterHttpResponse::mapperTo)
                );
    }

    @Override
    public ResponseEntity<?> present(final Throwable throwable) {

        logger.error("Ocorreu um erro ao tentar recuperar os cecanes.", throwable);
        return ResponseEntity.internalServerError().body(
                new ErrorInfo("Ocorreu um erro ao tentar recuperar os cecanes. Tente novamente mais tarde.")
        );
    }

}
