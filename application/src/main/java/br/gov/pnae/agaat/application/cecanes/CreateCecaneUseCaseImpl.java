package br.gov.pnae.agaat.application.cecanes;

import jakarta.inject.Named;

@Named
public class CreateCecaneUseCaseImpl extends CreateCecaneUseCase {

    @Override
    public Output execute(final Input input) {

        final var cecane = input.toAggregate();

        System.out.println("Criando cecane: " + cecane.getName());

        // salvar no banco de dados

        return new Output(cecane.getId().getValue());
    }

}
