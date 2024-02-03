package br.gov.pnae.agaat.application.cecanes;

import br.gov.pnae.agaat.application.UseCase;
import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneFactory;
import br.gov.pnae.agaat.domain.cecanes.fields.CecaneNome;
import jakarta.inject.Named;

@Named
public class CreateCecaneUseCase extends UseCase<CreateCecaneUseCase.Input, CreateCecaneUseCase.Output> {

    public Output execute(final Input input) {

        final var cecane = input.toAggregate();

        System.out.println("Criando cecane: " + cecane.getName());

        // salvar no banco de dados

        return Output.fromAggregate(cecane);
    }

    public record Input(String nome) {

        public Cecane toAggregate() {

            final var nome = new CecaneNome(this.nome);

            return CecaneFactory.create(nome);
        }

    }

    public record Output(Long id) {

        public static Output fromAggregate(final Cecane cecane) {
            final var id = cecane.getId().getValue();

            return new Output(
                    id
            );
        }

    }

}
