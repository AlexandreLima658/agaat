package br.gov.pnae.agaat.application;

public abstract class NullaryUseCase<OUT> {
    public abstract OUT execute();

    public <T> T execute(Presenter<OUT, T> presenter) {
        try {
            return presenter.present(execute());
        } catch (Throwable throwable) {
            return presenter.present(throwable);
        }
    }
}
