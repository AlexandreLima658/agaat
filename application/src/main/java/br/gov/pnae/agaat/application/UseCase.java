package br.gov.pnae.agaat.application;

public abstract class UseCase<IN, OUT> {
    public abstract OUT execute(IN input);

    public <T> T execute(IN input, Presenter<OUT, T> presenter) {
        try {
            return presenter.present(execute(input));
        } catch (Throwable throwable) {
            return presenter.present(throwable);
        }
    }
}
