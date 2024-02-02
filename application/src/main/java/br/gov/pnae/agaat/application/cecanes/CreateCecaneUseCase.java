package br.gov.pnae.agaat.application.cecanes;

import br.gov.pnae.agaat.application.UseCase;
import br.gov.pnae.agaat.domain.cecanes.Cecane;
import br.gov.pnae.agaat.domain.cecanes.CecaneFactory;
import br.gov.pnae.agaat.domain.cecanes.fields.CecaneNome;

public abstract class CreateCecaneUseCase extends UseCase<CreateCecaneUseCase.Input, CreateCecaneUseCase.Output> {

    public record Input(
            String nome
    ) {

        public Cecane toAggregate() {
            return CecaneFactory.create(
                    new CecaneNome(nome)
            );
        }

    }

    public record Output(Long id) {
    }


}
