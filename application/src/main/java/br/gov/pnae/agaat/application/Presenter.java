package br.gov.pnae.agaat.application;

public interface Presenter<IN, OUT> {

    OUT present(IN data);

    OUT present(final Throwable throwable);

}
