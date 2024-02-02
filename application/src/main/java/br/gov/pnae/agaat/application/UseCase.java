package br.gov.pnae.agaat.application;

public abstract class UseCase<IN, OUT> {
    public abstract OUT execute(IN input);
}
